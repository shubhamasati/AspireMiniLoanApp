package com.aspire.model;

import java.util.Objects;
import com.aspire.model.Repayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoanApprovalResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T07:11:26.518236400+05:30[Asia/Calcutta]")
public class LoanApprovalResponse   {
  @JsonProperty("status")
  private String status = null;

  @JsonProperty("application_id")
  private Long applicationId = null;

  @JsonProperty("loan_id")
  private Long loanId = null;

  @JsonProperty("amount")
  private String amount = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("startDate")
  private Date startDate = null;

  @JsonProperty("user_id")
  private Long userId = null;

  @JsonProperty("repayments")
  @Valid
  private List<Repayment> repayments = null;

  public LoanApprovalResponse status(String status) {
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

  public LoanApprovalResponse applicationId(Long applicationId) {
    this.applicationId = applicationId;
    return this;
  }

  /**
   * Get applicationId
   * @return applicationId
  **/
  @ApiModelProperty(value = "")
  
    public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }

  public LoanApprovalResponse loanId(Long loanId) {
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

  public LoanApprovalResponse amount(String amount) {
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

  public LoanApprovalResponse term(Integer term) {
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

  public LoanApprovalResponse startDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public LoanApprovalResponse userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")
  
    public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LoanApprovalResponse repayments(List<Repayment> repayments) {
    this.repayments = repayments;
    return this;
  }

  public LoanApprovalResponse addRepaymentsItem(Repayment repaymentsItem) {
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
    LoanApprovalResponse loanApprovalResponse = (LoanApprovalResponse) o;
    return Objects.equals(this.status, loanApprovalResponse.status) &&
        Objects.equals(this.applicationId, loanApprovalResponse.applicationId) &&
        Objects.equals(this.loanId, loanApprovalResponse.loanId) &&
        Objects.equals(this.amount, loanApprovalResponse.amount) &&
        Objects.equals(this.term, loanApprovalResponse.term) &&
        Objects.equals(this.startDate, loanApprovalResponse.startDate) &&
        Objects.equals(this.userId, loanApprovalResponse.userId) &&
        Objects.equals(this.repayments, loanApprovalResponse.repayments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, applicationId, loanId, amount, term, startDate, userId, repayments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoanApprovalResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
    sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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
