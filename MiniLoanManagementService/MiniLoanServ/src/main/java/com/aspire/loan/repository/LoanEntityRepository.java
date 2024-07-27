package com.aspire.loan.repository;

import com.aspire.loan.model.entity.LoanEntity;
import com.aspire.loan.model.entity.LoanRepaymentEntity;
import com.aspire.loan.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanEntityRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findAllByUser(UserEntity user);
}