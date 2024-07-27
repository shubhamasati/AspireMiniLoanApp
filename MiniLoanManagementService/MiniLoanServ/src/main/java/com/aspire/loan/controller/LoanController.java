package com.aspire.loan.controller;

import com.aspire.api.LoanApi;
import com.aspire.loan.service.LoanService;
import com.aspire.model.Loan;
import com.aspire.model.Repayment;
import com.aspire.model.RepaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController implements LoanApi {
    @Autowired
    private LoanService loanService;

    @Override
    public ResponseEntity<List<Loan>> getLoanList() {
        List<Loan> loanList = loanService.getLoanList();
        return ResponseEntity.ok(loanList);
    }

    @Override
    public ResponseEntity<Repayment> loanRepayment(RepaymentRequest request, Long loanId, Long repaymentId) {
        Repayment repaymentResponse = loanService.settleRepayment(request, loanId, repaymentId);
        return ResponseEntity.ok(repaymentResponse);
    }


}
