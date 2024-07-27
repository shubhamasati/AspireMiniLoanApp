package com.aspire.loan.service;

import com.aspire.loan.model.entity.LoanEntity;
import com.aspire.loan.model.entity.LoanRepaymentEntity;
import com.aspire.model.Loan;
import com.aspire.model.LoanApplicationResponse;
import com.aspire.model.Repayment;
import com.aspire.model.RepaymentRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LoanService {
    List<Loan> getLoanList();
    LoanEntity getLoanById(Long id);
    LoanEntity updateLoan(LoanEntity loanEntity);
    List<LoanRepaymentEntity> createRepayments(LoanEntity loanEntity);
    Repayment settleRepayment(RepaymentRequest request, Long loanId, Long repaymentId);
    List<LoanRepaymentEntity> fetchRepaymentsByLoanId(LoanEntity loan);
}
