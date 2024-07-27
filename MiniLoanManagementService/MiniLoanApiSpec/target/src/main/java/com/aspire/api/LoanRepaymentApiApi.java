/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.16).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.aspire.api;

import com.aspire.model.Repayment;
import com.aspire.model.RepaymentRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T03:46:38.067372300+05:30[Asia/Calcutta]")
@Api(value = "LoanRepaymentApi", description = "the LoanRepaymentApi API")
public interface LoanRepaymentApiApi {

    @ApiOperation(value = "Add a repayment", nickname = "loanRepayment", notes = "Repayment of Loan", response = Repayment.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "Loan Repayment Api", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Repayment added successfully", response = Repayment.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Loan not found") })
    @RequestMapping(value = "/api/loans/{loanId}/repayments/{repaymentId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Repayment> loanRepayment(@ApiParam(value = "" ,required=true )  @Valid @RequestBody RepaymentRequest body
,@ApiParam(value = "",required=true) @PathVariable("loanId") Long loanId
);

}
