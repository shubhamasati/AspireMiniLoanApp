package com.aspire.loan.model.entity;

import com.aspire.loan.enums.LoanApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class LoanApplicationEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amount;

    private Integer term;

    @Enumerated(EnumType.STRING)
    private LoanApplicationStatus status;

    @ManyToOne
    private UserEntity user;
}
