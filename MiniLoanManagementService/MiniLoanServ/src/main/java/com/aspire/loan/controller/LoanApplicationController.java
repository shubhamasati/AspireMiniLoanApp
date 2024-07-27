package com.aspire.loan.controller;

import com.aspire.api.LoanApplicationApi;
import com.aspire.loan.service.LoanApplicationService;
import com.aspire.loan.service.LoanService;
import com.aspire.model.Loan;
import com.aspire.model.LoanApplication;
import com.aspire.model.LoanApplicationResponse;
import com.aspire.model.LoanApprovalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanApplicationController implements LoanApplicationApi {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Override
    public ResponseEntity<LoanApprovalResponse> approveLoanApplication(Long applicationId) {
        LoanApprovalResponse loanApprovalResponse = loanApplicationService.approveLoanApplication(applicationId);
        return ResponseEntity.ok(loanApprovalResponse);
    }

    @Override
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(LoanApplication application) {
        LoanApplicationResponse applicationResponse = loanApplicationService.createApplication(application);
        return ResponseEntity.ok(applicationResponse);
    }


}
