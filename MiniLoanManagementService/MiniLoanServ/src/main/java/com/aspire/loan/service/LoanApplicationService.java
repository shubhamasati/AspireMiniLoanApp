package com.aspire.loan.service;

import com.aspire.model.LoanApplication;
import com.aspire.model.LoanApplicationResponse;
import com.aspire.model.LoanApprovalResponse;

import java.util.List;

public interface LoanApplicationService {
    LoanApplicationResponse createApplication(LoanApplication loan);
    LoanApprovalResponse approveLoanApplication(Long applicationId);
}
