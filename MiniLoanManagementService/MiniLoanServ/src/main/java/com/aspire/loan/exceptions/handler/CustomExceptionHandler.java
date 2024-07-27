package com.aspire.loan.exceptions.handler;

import com.aspire.loan.enums.CommonError;
import com.aspire.loan.exceptions.ApplicationException;
import com.aspire.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getError(), ex.getHttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.INTERNAL_SERVER_ERROR.getName());
        error.setMessage(CommonError.INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(AccessDeniedException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setName(CommonError.NOT_AUTHORIZED.getName());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
