package com.aspire.loan.model.entity;

import com.aspire.loan.enums.RepaymentStatus;
import com.aspire.model.Repayment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class LoanRepaymentEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String repaymentAmount;

    private String  principleAmount;

    private String interestCharged;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private RepaymentStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;

    @ManyToOne
    private LoanEntity loan;
}
