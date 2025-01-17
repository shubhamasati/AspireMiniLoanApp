/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.16).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.aspire.api;

import com.aspire.model.LoginRequest;
import com.aspire.model.LoginResponse;
import com.aspire.model.User;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-27T07:11:26.518236400+05:30[Asia/Calcutta]")
@Api(value = "UserAuthApi", description = "the UserAuthApi API")
public interface UserAuthApiApi {

    @ApiOperation(value = "Log in User", nickname = "userLogin", notes = "Get user logged in", response = LoginResponse.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "User Auth Api", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Logged-in successfully", response = LoginResponse.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Loan not found") })
    @RequestMapping(value = "/api/users/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<LoginResponse> userLogin(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginRequest body
);


    @ApiOperation(value = "Sign-up User", nickname = "userSignUp", notes = "Get user registered", response = User.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "User Auth Api", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Logged-in successfully", response = User.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Loan not found") })
    @RequestMapping(value = "/api/users/signup",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<User> userSignUp(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body
);

}
