package com.aspire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repayment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T07:11:26.518236400+05:30[Asia/Calcutta]")
public class Repayment   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("repayment_amount")
  private String repaymentAmount = null;

  @JsonProperty("principle_amount")
  private String principleAmount = null;

  @JsonProperty("interest_charged")
  private String interestCharged = null;

  @JsonProperty("dueDate")
  private Date dueDate = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("paid_at")
  private String paidAt = null;

  public Repayment id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Repayment repaymentAmount(String repaymentAmount) {
    this.repaymentAmount = repaymentAmount;
    return this;
  }

  /**
   * Get repaymentAmount
   * @return repaymentAmount
  **/
  @ApiModelProperty(value = "")
  
    public String getRepaymentAmount() {
    return repaymentAmount;
  }

  public void setRepaymentAmount(String repaymentAmount) {
    this.repaymentAmount = repaymentAmount;
  }

  public Repayment principleAmount(String principleAmount) {
    this.principleAmount = principleAmount;
    return this;
  }

  /**
   * Get principleAmount
   * @return principleAmount
  **/
  @ApiModelProperty(value = "")
  
    public String getPrincipleAmount() {
    return principleAmount;
  }

  public void setPrincipleAmount(String principleAmount) {
    this.principleAmount = principleAmount;
  }

  public Repayment interestCharged(String interestCharged) {
    this.interestCharged = interestCharged;
    return this;
  }

  /**
   * Get interestCharged
   * @return interestCharged
  **/
  @ApiModelProperty(value = "")
  
    public String getInterestCharged() {
    return interestCharged;
  }

  public void setInterestCharged(String interestCharged) {
    this.interestCharged = interestCharged;
  }

  public Repayment dueDate(Date dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Repayment status(String status) {
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

  public Repayment paidAt(String paidAt) {
    this.paidAt = paidAt;
    return this;
  }

  /**
   * Get paidAt
   * @return paidAt
  **/
  @ApiModelProperty(value = "")
  
    public String getPaidAt() {
    return paidAt;
  }

  public void setPaidAt(String paidAt) {
    this.paidAt = paidAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Repayment repayment = (Repayment) o;
    return Objects.equals(this.id, repayment.id) &&
        Objects.equals(this.repaymentAmount, repayment.repaymentAmount) &&
        Objects.equals(this.principleAmount, repayment.principleAmount) &&
        Objects.equals(this.interestCharged, repayment.interestCharged) &&
        Objects.equals(this.dueDate, repayment.dueDate) &&
        Objects.equals(this.status, repayment.status) &&
        Objects.equals(this.paidAt, repayment.paidAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, repaymentAmount, principleAmount, interestCharged, dueDate, status, paidAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Repayment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    repaymentAmount: ").append(toIndentedString(repaymentAmount)).append("\n");
    sb.append("    principleAmount: ").append(toIndentedString(principleAmount)).append("\n");
    sb.append("    interestCharged: ").append(toIndentedString(interestCharged)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    paidAt: ").append(toIndentedString(paidAt)).append("\n");
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
