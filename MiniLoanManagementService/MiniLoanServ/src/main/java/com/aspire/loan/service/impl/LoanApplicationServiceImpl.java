package com.aspire.loan.service.impl;

import com.aspire.loan.enums.CommonError;
import com.aspire.loan.enums.LoanApplicationStatus;
import com.aspire.loan.enums.LoanStatus;
import com.aspire.loan.exceptions.ExceptionUtil;
import com.aspire.loan.model.entity.LoanEntity;
import com.aspire.loan.model.entity.LoanApplicationEntity;
import com.aspire.loan.model.entity.LoanRepaymentEntity;
import com.aspire.loan.repository.LoanApplicationRepository;
import com.aspire.loan.repository.LoanEntityRepository;
import com.aspire.loan.service.LoanApplicationService;
import com.aspire.loan.service.LoanService;
import com.aspire.loan.service.UserService;
import com.aspire.loan.utils.SecurityUtil;
import com.aspire.loan.utils.Utils;
import com.aspire.model.LoanApplication;
import com.aspire.model.LoanApplicationResponse;
import com.aspire.model.LoanApprovalResponse;
import com.aspire.model.Repayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanEntityRepository loanEntityRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private LoanService loanService;

    @Override
    public LoanApplicationResponse createApplication(LoanApplication loanApplication) {
        LoanApplicationResponse response = new LoanApplicationResponse();

        validateLoanApplication(loanApplication);


        LoanApplicationEntity loanApplicationEntity = prepareLoadEntity(loanApplication);
        LoanApplicationEntity saveApplication = loanApplicationRepository.save(loanApplicationEntity);
        Long loanApplicationId = saveApplication.getId();
        response.setApplicationId(loanApplicationId);
        response.setAmount(loanApplication.getAmount());
        response.setTerm(loanApplication.getTerm());
        response.setStatus("APPLICATION_SUBMITTED");
        return response;
    }

    private LoanApplicationEntity prepareLoadEntity(LoanApplication loanApplication) {
        LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity();
        loanApplicationEntity.setStatus(LoanApplicationStatus.PENDING);
        loanApplicationEntity.setAmount(loanApplication.getAmount());
        loanApplicationEntity.setTerm(loanApplication.getTerm());
        loanApplicationEntity.setUser(userService.loadUserByUsername(SecurityUtil.getUsername()));
        return loanApplicationEntity;
    }

    private void validateLoanApplication(LoanApplication loanApplication) {
        if (Objects.isNull(loanApplication)) {
            log.warn("Request is empty; can't be processed further");
            ExceptionUtil.throwBadRequestException(null, null, CommonError.INVALID_REQUEST.getName(),
                    CommonError.INVALID_REQUEST.getMessage(), null);
        }

        try {
            double loanAmount = Double.parseDouble(loanApplication.getAmount());
            if (Objects.isNull(loanApplication.getAmount()) || loanAmount < 1) {
                log.warn("Invalid requested amount; can't be processed further");
                ExceptionUtil.throwBadRequestException(null, null, CommonError.INVALID_PARAMETER_VALUE.getName(),
                        null, CommonError.INVALID_PARAMETER_VALUE.getMessage());
            }
        } catch (NumberFormatException nfe) {
            ExceptionUtil.throwBadRequestException(loanApplication.getAmount(), "amount", CommonError.INVALID_PARAMETER_VALUE.getName(),
                    null, CommonError.INVALID_PARAMETER_VALUE.getMessage());
        }
    }


    @Override
    @Transactional
    public LoanApprovalResponse approveLoanApplication(Long applicationId) {
        LoanApprovalResponse response = new LoanApprovalResponse();
        if (Objects.isNull(applicationId) || applicationId < 1) {
            log.warn("Invalid applicationId provided.");
            ExceptionUtil.throwBadRequestException(Objects.requireNonNullElse(applicationId, "null").toString(),
                    "loanId", CommonError.INVALID_REQUEST.getName(), "path", CommonError.INVALID_REQUEST.getMessage());
        }

        Optional<LoanApplicationEntity> opLoanApplication = loanApplicationRepository.findById(applicationId);

        if (opLoanApplication.isEmpty()) {
            ExceptionUtil.throwBadRequestException(applicationId.toString(), "applicationId", CommonError.RESOURCE_NOT_FOUND.getName(), null, CommonError.RESOURCE_NOT_FOUND.getMessage());
        }
        LoanApplicationEntity loanApplication = opLoanApplication.get();
        if (loanApplication.getStatus().equals(LoanApplicationStatus.PENDING)) {
            loanApplication.setStatus(LoanApplicationStatus.APPROVED);
            try {
                loanApplicationRepository.save(loanApplication);
            } catch (Exception e) {
                log.error("Error occurred while approving the Loan Application");
                ExceptionUtil.throwInternalServerException("Error occurred while approving the Loan Application");
            }

            LoanEntity loanEntity = createNewLoanForUser(loanApplication);
            LoanEntity savedLoan = loanEntityRepository.save(loanEntity);
            List<LoanRepaymentEntity> repayments = loanService.createRepayments(savedLoan);

            response.setRepayments(mapRepaymentResponse(repayments));
            response.setStatus(LoanApplicationStatus.APPROVED.name());
            response.setAmount(loanApplication.getAmount());
            response.setTerm(loanApplication.getTerm());
            response.setApplicationId(applicationId);
            response.setLoanId(savedLoan.getId());
            response.setUserId(savedLoan.getUser().getId());
            response.setStartDate(savedLoan.getInsertedAt());
        } else {
            log.warn("Operation not permitted");
            ExceptionUtil.throwBadRequestException(null, null,
                    CommonError.INVALID_REQUEST.getName(), null, CommonError.INVALID_REQUEST.getMessage());
        }
        return response;
    }

    private List<Repayment> mapRepaymentResponse(List<LoanRepaymentEntity> repayments) {
        List<Repayment> repaymentList = new ArrayList<>();
        for (LoanRepaymentEntity entity: repayments) {
            Repayment repayment = new Repayment();
            repayment.setId(entity.getId());
            repayment.setRepaymentAmount(entity.getRepaymentAmount());
            repayment.setInterestCharged(entity.getInterestCharged());
            repayment.setPrincipleAmount(entity.getPrincipleAmount());
            repayment.setStatus(entity.getStatus().name());
            repayment.setDueDate(entity.getDueDate());
            repayment.paidAt(Objects
                    .requireNonNullElse(entity.getPaidAt(), "null").toString());
            repaymentList.add(repayment);
        }
        return repaymentList;
    }

    private LoanEntity createNewLoanForUser(LoanApplicationEntity loanApplication) {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setStatus(LoanStatus.SANCTIONED);
        loanEntity.setPrincipleAmount(loanApplication.getAmount());
        loanEntity.setTerm(loanApplication.getTerm());
        loanEntity.setInterestRate(calculateInterestRate(loanApplication.getAmount()));
        loanEntity.setProcessingFee(calculateProcessingFee(loanApplication.getAmount()));
        loanEntity.setUser(loanApplication.getUser());
        return loanEntity;
    }

    private String calculateProcessingFee(String amount) {
        return Utils.formatDouble(Double.parseDouble(amount) * 0.03); // processing fee is flat 3% of the amount
    }

    private String calculateInterestRate(String amount) {
        double dAmount = Double.parseDouble(amount);
        if (dAmount > 500000) {
            return "10.5";
        } else if (dAmount > 250000) {
            return "11.0";
        } else if (dAmount > 100000) {
            return "11.5";
        } else {
            return "12.0";
        }
    }
}
