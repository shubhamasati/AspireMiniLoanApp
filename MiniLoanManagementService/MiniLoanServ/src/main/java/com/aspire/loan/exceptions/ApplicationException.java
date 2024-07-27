package com.aspire.loan.exceptions;

import com.aspire.model.ErrorResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApplicationException extends RuntimeException {

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public ApplicationException(ErrorResponse error, HttpStatus httpStatus, HttpHeaders httpHeaders) {
        super(error.getMessage());
        this.error = error;
        this.httpStatus = httpStatus;
        this.httpHeaders = httpHeaders;
    }

    private ErrorResponse error;
    private HttpStatus httpStatus;
    private HttpHeaders httpHeaders;
}
