package com.aspire.model;

import java.util.Objects;
import com.aspire.model.ErrorDetails;
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
 * ErrorResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T07:11:26.518236400+05:30[Asia/Calcutta]")
public class ErrorResponse   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("debugId")
  private String debugId = null;

  @JsonProperty("details")
  @Valid
  private List<ErrorDetails> details = null;

  public ErrorResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResponse debugId(String debugId) {
    this.debugId = debugId;
    return this;
  }

  /**
   * Get debugId
   * @return debugId
  **/
  @ApiModelProperty(value = "")
  
    public String getDebugId() {
    return debugId;
  }

  public void setDebugId(String debugId) {
    this.debugId = debugId;
  }

  public ErrorResponse details(List<ErrorDetails> details) {
    this.details = details;
    return this;
  }

  public ErrorResponse addDetailsItem(ErrorDetails detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<ErrorDetails>();
    }
    this.details.add(detailsItem);
    return this;
  }

  /**
   * Get details
   * @return details
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ErrorDetails> getDetails() {
    return details;
  }

  public void setDetails(List<ErrorDetails> details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.name, errorResponse.name) &&
        Objects.equals(this.message, errorResponse.message) &&
        Objects.equals(this.debugId, errorResponse.debugId) &&
        Objects.equals(this.details, errorResponse.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, message, debugId, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    debugId: ").append(toIndentedString(debugId)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
