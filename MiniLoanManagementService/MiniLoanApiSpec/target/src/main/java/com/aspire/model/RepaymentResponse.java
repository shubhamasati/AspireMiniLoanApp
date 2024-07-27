package com.aspire.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RepaymentResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T07:11:26.518236400+05:30[Asia/Calcutta]")
public class RepaymentResponse   {
  @JsonProperty("status")
  private String status = null;

  @JsonProperty("repayment_id")
  private Long repaymentId = null;

  public RepaymentResponse status(String status) {
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

  public RepaymentResponse repaymentId(Long repaymentId) {
    this.repaymentId = repaymentId;
    return this;
  }

  /**
   * Get repaymentId
   * @return repaymentId
  **/
  @ApiModelProperty(value = "")
  
    public Long getRepaymentId() {
    return repaymentId;
  }

  public void setRepaymentId(Long repaymentId) {
    this.repaymentId = repaymentId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RepaymentResponse repaymentResponse = (RepaymentResponse) o;
    return Objects.equals(this.status, repaymentResponse.status) &&
        Objects.equals(this.repaymentId, repaymentResponse.repaymentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, repaymentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RepaymentResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    repaymentId: ").append(toIndentedString(repaymentId)).append("\n");
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
