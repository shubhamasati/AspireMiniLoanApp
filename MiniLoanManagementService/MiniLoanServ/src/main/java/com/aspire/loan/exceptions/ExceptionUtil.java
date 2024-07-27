package com.aspire.loan.exceptions;

import com.aspire.loan.enums.CommonError;
import com.aspire.model.ErrorDetails;
import com.aspire.model.ErrorResponse;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ExceptionUtil {


    public static void throwBadRequestException(String value, String field, String issue, String location, String description) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setField(field);
        errorDetails.setValue(value);
        errorDetails.setIssue(issue);
        errorDetails.setLocation(location);
        errorDetails.setDescription(description);
        List<ErrorDetails> errorDetailsList = Collections.singletonList(errorDetails);
        throwBadRequestException(errorDetailsList);
    }

    public static void throwBadRequestException(List<ErrorDetails> errorDetails) {
        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.INVALID_REQUEST.getName());
        error.setMessage(CommonError.INVALID_REQUEST.getMessage());
        error.setDetails(errorDetails);
        throw new ApplicationException(error, HttpStatus.BAD_REQUEST, new HttpHeaders());
    }

    public static void throwResourceNotFoundException(String value, String field, String location) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setIssue(CommonError.INVALID_RESOURCE_ID.name());
        errorDetails.setDescription(CommonError.INVALID_RESOURCE_ID.getMessage());
        errorDetails.setValue(value);
        errorDetails.setField(field);
        errorDetails.setLocation(location);

        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.RESOURCE_NOT_FOUND.name());
        error.setMessage(CommonError.RESOURCE_NOT_FOUND.getMessage());
        error.setDetails(Collections.singletonList(errorDetails));

        throw new ApplicationException(error, HttpStatus.NOT_FOUND, new HttpHeaders());
    }

    public static void throwForbiddenException() {
        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.NOT_AUTHORIZED.name());
        error.setMessage(CommonError.NOT_AUTHORIZED.getMessage());
        throw new ApplicationException(error, HttpStatus.FORBIDDEN, new HttpHeaders());
    }

    public static void throwUnprocessableEntityException(String value, String field, String issue, String location, String description) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setField(field);
        errorDetails.setValue(value);
        errorDetails.setIssue(issue);
        errorDetails.setLocation(location);
        errorDetails.setDescription(description);
        List<ErrorDetails> errorDetailsList = Collections.singletonList(errorDetails);
        throwUnprocessableEntityException(errorDetailsList);
    }

    public static void throwUnprocessableEntityException(List<ErrorDetails> errorDetails) {
        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.UNPROCESSABLE_ENTITY.getName());
        error.setMessage(CommonError.UNPROCESSABLE_ENTITY.getMessage());
        error.setDebugId(MDC.get("correlationId"));
        error.setDetails(errorDetails);
        throw new ApplicationException(error, HttpStatus.UNPROCESSABLE_ENTITY, new HttpHeaders());
    }

    public static void throwInternalServerException(String description) {

        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.INTERNAL_SERVER_ERROR.name());
        error.setMessage(CommonError.INTERNAL_SERVER_ERROR.getMessage());
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setIssue(CommonError.INTERNAL_SERVER_ERROR.name());
        errorDetails.setDescription(description);
        error.setDetails(Collections.singletonList(errorDetails));

        throw new ApplicationException(error, HttpStatus.INTERNAL_SERVER_ERROR, new HttpHeaders());
    }

    public static void throwInternalServerException(String issue, String description) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setField(null);
        errorDetails.setValue(null);
        errorDetails.setIssue(issue);
        errorDetails.setLocation(null);
        errorDetails.setDescription(description);
        List<ErrorDetails> errorDetailsList = Collections.singletonList(errorDetails);
        throwInternalServerException(errorDetailsList);
    }

    public static void throwInternalServerException(List<ErrorDetails> errorDetails) {
        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.INTERNAL_SERVER_ERROR.getName());
        error.setMessage(CommonError.INTERNAL_SERVER_ERROR.getMessage());
        error.setDetails(errorDetails);
        throw new ApplicationException(error, HttpStatus.INTERNAL_SERVER_ERROR, new HttpHeaders());
    }

}
