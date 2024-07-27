package com.aspire.model;

import java.util.Objects;
import com.aspire.model.Repayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Loan
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T07:11:26.518236400+05:30[Asia/Calcutta]")
public class Loan   {
  @JsonProperty("loan_id")
  private Long loanId = null;

  @JsonProperty("amount")
  private String amount = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("start_date")
  private String startDate = null;

  @JsonProperty("interest_rate")
  private String interestRate = null;

  @JsonProperty("repayments")
  @Valid
  private List<Repayment> repayments = null;

  public Loan loanId(Long loanId) {
    this.loanId = loanId;
    return this;
  }

  /**
   * Get loanId
   * @return loanId
  **/
  @ApiModelProperty(value = "")
  
    public Long getLoanId() {
    return loanId;
  }

  public void setLoanId(Long loanId) {
    this.loanId = loanId;
  }

  public Loan amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(value = "")
  
    public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public Loan status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  
    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Loan term(Integer term) {
    this.term = term;
    return this;
  }

  /**
   * Get term
   * @return term
  **/
  @ApiModelProperty(value = "")
  
    public Integer getTerm() {
    return term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }

  public Loan startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(value = "")
  
    public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public Loan interestRate(String interestRate) {
    this.interestRate = interestRate;
    return this;
  }

  /**
   * Get interestRate
   * @return interestRate
  **/
  @ApiModelProperty(value = "")
  
    public String getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  public Loan repayments(List<Repayment> repayments) {
    this.repayments = repayments;
    return this;
  }

  public Loan addRepaymentsItem(Repayment repaymentsItem) {
    if (this.repayments == null) {
      this.repayments = new ArrayList<Repayment>();
    }
    this.repayments.add(repaymentsItem);
    return this;
  }

  /**
   * Get repayments
   * @return repayments
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Repayment> getRepayments() {
    return repayments;
  }

  public void setRepayments(List<Repayment> repayments) {
    this.repayments = repayments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Loan loan = (Loan) o;
    return Objects.equals(this.loanId, loan.loanId) &&
        Objects.equals(this.amount, loan.amount) &&
        Objects.equals(this.status, loan.status) &&
        Objects.equals(this.term, loan.term) &&
        Objects.equals(this.startDate, loan.startDate) &&
        Objects.equals(this.interestRate, loan.interestRate) &&
        Objects.equals(this.repayments, loan.repayments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loanId, amount, status, term, startDate, interestRate, repayments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Loan {\n");
    
    sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    interestRate: ").append(toIndentedString(interestRate)).append("\n");
    sb.append("    repayments: ").append(toIndentedString(repayments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
