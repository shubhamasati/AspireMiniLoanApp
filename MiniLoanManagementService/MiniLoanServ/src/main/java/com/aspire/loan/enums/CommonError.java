package com.aspire.loan.enums;

import lombok.Getter;

@Getter
public enum CommonError {
    BAD_REQUEST("BAD_REQUEST", "Request is not well-formed, syntactically incorrect, or violates schema"),
    INVALID_REQUEST("INVALID_REQUEST", "Request is not well-formed, syntactically incorrect, or violates schema"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An internal server error has occurred."),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "The specified resource does not exist"),
    NOT_AUTHORIZED("NOT_AUTHORIZED", "Authorization failed due to insufficient permissions."),
    INVALID_RESOURCE_ID("INVALID_RESOURCE_ID", "Requested resource ID was not found."),
    PERMISSION_DENIED("PERMISSION_DENIED", "You do not have permission to access or perform operations on this resource."),
    HEADER_MISSING("HEADER_MISSING", "The mandatory header is missing"),
    INVALID_PARAMETER_VALUE("INVALID_PARAMETER_VALUE", "The value of a field is invalid. please check the value and try again."),
    UNPROCESSABLE_ENTITY("UNPROCESSABLE_ENTITY", "The requested action could not be performed, semantically incorrect, or failed business validation"),
    MISSING_REQUIRED_PARAMETER("MISSING_REQUIRED_PARAMETER", "The value of a required field is missing."),
    REQUEST_NOT_SUPPORTED("REQUEST_NOT_SUPPORTED", "The request is not supported."),
    WEB_DB_CLIENT_RESPONSE_FAILED("WEB_DB_CLIENT_RESPONSE_FAILED", "Failed to get response from web db client. DB operation failed.");

    private final String name;
    private final String message;

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    CommonError(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
