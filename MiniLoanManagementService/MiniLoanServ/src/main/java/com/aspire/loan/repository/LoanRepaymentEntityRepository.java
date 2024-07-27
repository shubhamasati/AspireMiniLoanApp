package com.aspire.loan.repository;

import com.aspire.loan.model.entity.LoanEntity;
import com.aspire.loan.model.entity.LoanRepaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepaymentEntityRepository extends JpaRepository<LoanRepaymentEntity, Long> {
    List<LoanRepaymentEntity> findAllByLoan(LoanEntity loan);
}