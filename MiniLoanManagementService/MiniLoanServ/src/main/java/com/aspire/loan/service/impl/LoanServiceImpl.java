package com.aspire.loan.service.impl;

import com.aspire.loan.enums.CommonError;
import com.aspire.loan.enums.LoanStatus;
import com.aspire.loan.enums.RepaymentStatus;
import com.aspire.loan.enums.UserRole;
import com.aspire.loan.exceptions.ExceptionUtil;
import com.aspire.loan.model.entity.LoanEntity;
import com.aspire.loan.model.entity.LoanRepaymentEntity;
import com.aspire.loan.model.entity.UserEntity;
import com.aspire.loan.repository.LoanEntityRepository;
import com.aspire.loan.repository.LoanRepaymentEntityRepository;
import com.aspire.loan.service.LoanService;
import com.aspire.loan.service.UserService;
import com.aspire.loan.utils.SecurityUtil;
import com.aspire.loan.utils.Utils;
import com.aspire.model.Loan;
import com.aspire.model.Repayment;
import com.aspire.model.RepaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private UserService userService;

    @Autowired
    private LoanEntityRepository loanEntityRepository;

    @Autowired
    private LoanRepaymentEntityRepository loanRepaymentEntityRepository;


    @Override
    public List<Loan> getLoanList() {
        String username = SecurityUtil.getUsername();
        UserEntity user = userService.loadUserByUsername(username);

        List<LoanEntity> loanList = null;
        if (UserRole.ADMIN.equals(user.getRole())) {
            loanList = getLoanListForAdmin(user);
        } else if (UserRole.CUSTOMER.equals(user.getRole())) {
            loanList = getLoanListForUser(user);
        }
        if (CollectionUtils.isEmpty(loanList)) {
            return new ArrayList<>();
        }
        return prepareLoanListResponse(loanList);
    }

    @Override
    public LoanEntity updateLoan(LoanEntity loanEntity) {
        if (Objects.nonNull(loanEntity)) {
            return loanEntityRepository.save(loanEntity);
        }
        return null;
    }

    @Override
    public LoanEntity getLoanById(Long id) {
        return loanEntityRepository.findById(id).orElse(null);
    }

    private List<Loan> prepareLoanListResponse(List<LoanEntity> loanList) {
        List<Loan> loans = new ArrayList<>();
        for (LoanEntity loanEntity: loanList) {
            Loan loan = new Loan();
            loan.setStatus(loanEntity.getStatus().name());
            loan.setLoanId(loanEntity.getId());
            loan.setAmount(loanEntity.getPrincipleAmount());
            loan.setTerm(loanEntity.getTerm());
            loan.setStartDate(loanEntity.getInsertedAt().toString());
            loan.setInterestRate(loanEntity.getInterestRate());
            List<LoanRepaymentEntity> list = fetchRepaymentsByLoanId(loanEntity);
            loan.setRepayments(mapToRepaymentList(list));
            loans.add(loan);
        }
        return loans;
    }

    @Override
    public List<LoanRepaymentEntity> createRepayments(LoanEntity loan) {
        Integer term = loan.getTerm();
        Double principleAmount = Double.valueOf(loan.getPrincipleAmount());
        Double interestRate = Double.valueOf(loan.getInterestRate());
        Double interestCharged = getInterestCharged(principleAmount, interestRate);

        double totalLoanAmountWithInterest = principleAmount + interestCharged;

        double repaymentAmount = getRepaymentAmount(principleAmount, interestCharged, term);
        List<LoanRepaymentEntity> repayments = new ArrayList<>();
        Date sanctionedDate = loan.getInsertedAt();
        Date repaymentDueDate = getNextRepaymentDate(sanctionedDate);
        for (int i = 1; i <= term; i++) {
            LoanRepaymentEntity repayment = new LoanRepaymentEntity();
            if (i < term) {
                repayment.setRepaymentAmount(Utils.formatDouble(repaymentAmount));
            } else {
                repayment.setRepaymentAmount(Utils
                        .formatDouble(totalLoanAmountWithInterest - ((term - 1) * repaymentAmount)));
            }
            repayment.setPrincipleAmount(
                    Utils.formatDouble(principleAmount / term));
            repayment.setInterestCharged(
                    Utils.formatDouble(interestCharged / term));
            repayment.setDueDate(repaymentDueDate);
            repayment.setStatus(RepaymentStatus.PENDING);
            repayment.setLoan(loan);
            repaymentDueDate = getNextRepaymentDate(repaymentDueDate);
            repayments.add(repayment);
        }
        return loanRepaymentEntityRepository.saveAll(repayments);
    }

    @Override
    public List<LoanRepaymentEntity> fetchRepaymentsByLoanId(LoanEntity loan) {
        return loanRepaymentEntityRepository.findAllByLoan(loan);
    }

    private List<Repayment> mapToRepaymentList(List<LoanRepaymentEntity> repaymentEntities) {
        List<Repayment> repaymentList = new ArrayList<>();
        for (LoanRepaymentEntity entity: repaymentEntities) {
            Repayment repayment = new Repayment();
            repayment.setRepaymentAmount(entity.getRepaymentAmount());
            repayment.setStatus(entity.getStatus().name());
            repayment.setDueDate(entity.getDueDate());
            repayment.setPaidAt(Objects.requireNonNullElse(entity.getPaidAt(), "null").toString());
            repayment.setId(entity.getId());
            repayment.setPrincipleAmount(entity.getPrincipleAmount());
            repayment.setInterestCharged(entity.getInterestCharged());
            repaymentList.add(repayment);
        }
        return repaymentList;
    }


    @Override
    public Repayment settleRepayment(RepaymentRequest request, Long loanId, Long repaymentId) {
        if (Objects.isNull(request)) {
            ExceptionUtil.throwBadRequestException(null, null, CommonError.INVALID_REQUEST.getName(),
                    null, CommonError.INVALID_REQUEST.getMessage());
        }
        String username = SecurityUtil.getUsername();

        UserEntity userEntity = userService.loadUserByUsername(username);

        double amount = Double.parseDouble(request.getAmount());
        Optional<LoanRepaymentEntity> byId = loanRepaymentEntityRepository.findById(repaymentId);
        if (byId.isPresent()) {
            LoanRepaymentEntity repaymentEntity = byId.get();
            if (!loanId.equals(repaymentEntity.getLoan().getId())
                    || !repaymentEntity.getLoan().getUser().equals(userEntity)) {
                ExceptionUtil.throwUnprocessableEntityException(loanId.toString(), "loanId",
                        CommonError.RESOURCE_NOT_FOUND.getName(), null,
                        CommonError.RESOURCE_NOT_FOUND.getMessage());
            }

            if (Double.parseDouble(repaymentEntity.getRepaymentAmount()) <= amount) {
                if (repaymentEntity.getDueDate().before(new Date())) {
                    repaymentEntity.setStatus(RepaymentStatus.PAID_LATE);
                } else {
                    repaymentEntity.setStatus(RepaymentStatus.PAID);
                }
                repaymentEntity.setPaidAt(new Date());
                LoanRepaymentEntity updatedRepayment = loanRepaymentEntityRepository.save(repaymentEntity);
                refreshLoanStatus(loanId);

                return prepareRepaymentResponse(updatedRepayment);
            } else {
                ExceptionUtil.throwUnprocessableEntityException(null, null, "AMOUNT_SHOULD_BE_GREATER", null,
                        "Amount should be greater or equal to the repayment amount");
            }
        }
        return null;
    }

    private Repayment prepareRepaymentResponse(LoanRepaymentEntity updatedRepayment) {
        Repayment repayment = new Repayment();
        repayment.setId(updatedRepayment.getId());
        repayment.setStatus(updatedRepayment.getStatus().name());
        repayment.setDueDate(updatedRepayment.getDueDate());
        repayment.setRepaymentAmount(updatedRepayment.getRepaymentAmount());
        repayment.setPrincipleAmount(updatedRepayment.getPrincipleAmount());
        repayment.setInterestCharged(updatedRepayment.getInterestCharged());
        repayment.setPaidAt(updatedRepayment.getPaidAt().toString());
        return repayment;
    }

    private void refreshLoanStatus(Long loanId) {
        Optional<LoanEntity> loanOptional = loanEntityRepository.findById(loanId);
        LoanEntity loan = loanOptional.get();
        List<LoanRepaymentEntity> repayments = loanRepaymentEntityRepository.findAllByLoan(loan);
        boolean isAllPaid = true;
        boolean isNonePaid = true;
        for (LoanRepaymentEntity repayment: repayments) {
            isAllPaid = isAllPaid & (RepaymentStatus.PAID.equals(repayment.getStatus())
                    || RepaymentStatus.PAID_LATE.equals(repayment.getStatus()));
            isNonePaid = isNonePaid & (RepaymentStatus.PENDING.equals(repayment.getStatus()));
        }

        if (isAllPaid) {
            loan.setStatus(LoanStatus.FULLY_PAID);
        } else if (!isNonePaid) {
            loan.setStatus(LoanStatus.PARTIALLY_PAID);
        }
        loanEntityRepository.save(loan);
    }

    private Double getRepaymentAmount(Double principleAmount, Double interestCharged, Integer term) {
        return BigDecimal.valueOf((principleAmount + interestCharged) / term)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Date getNextRepaymentDate(Date date) {
        return Date.from(date.toInstant().plus(7, ChronoUnit.DAYS));
    }

    private Double getInterestCharged(Double principleAmount, Double interestRate) {
        return BigDecimal.valueOf((principleAmount * interestRate) / 100)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

    }

    private List<LoanEntity> getLoanListForUser(UserEntity user) {
        return loanEntityRepository.findAllByUser(user);
    }

    private List<LoanEntity> getLoanListForAdmin(UserEntity user) {
        return loanEntityRepository.findAll();
    }
}
