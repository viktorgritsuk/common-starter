package com.common.starter.handler;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.common.starter.exception.external.ExternalErrorException;
import com.common.starter.exception.external.client.ClientExternalErrorException;
import com.common.starter.model.domain.CommonError;
import com.common.starter.model.response.ErrorResponse;
import com.common.starter.util.CommonResponseBuilder;
import com.common.starter.util.ExceptionHandlerUtils;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class handles generic exceptions and returns a customized error response.
 */
@RestControllerAdvice
@Order
@Slf4j
@RequiredArgsConstructor
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles and converts exceptions to errors.
     */
    private final ExceptionHandlerUtils exceptionHandlerUtils;

    /**
     * Builds api responses.
     */
    private final CommonResponseBuilder commonResponseBuilder;

    /**
     * This method handles an any Exception by generating a customized error response.
     *
     * @param exception The Exception that is being handled.
     * @return A ResponseEntity object with status code 500 (Internal Server Error) and a body containing the customized error response.
     * @throws IllegalArgumentException if the com.bae.ii.exception parameter is null.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(commonResponseBuilder.generateErrorResponse(exceptionHandlerUtils.getErrors(exception)));
    }

    /**
     * This method handles an ExternalErrorException by generating a customized error response.
     *
     * @param exception The ExternalErrorException that is being handled.
     * @return A ResponseEntity object with status code 500 (Internal Server Error) and a body containing the customized error response.
     * @throws IllegalArgumentException if the exception parameter is null.
     */
    @ExceptionHandler(ExternalErrorException.class)
    public final ResponseEntity<ErrorResponse> handleExternalErrorException(ExternalErrorException exception) {
        log.error(exception.getMessage(), exception);

        List<CommonError> errorList = switch (exception) {
            case ClientExternalErrorException e -> exceptionHandlerUtils.getClientErrors(e);
            default -> exception.getErrors();
        };

        return ResponseEntity
            .status(exception.getStatus())
            .body(commonResponseBuilder.generateErrorResponse(errorList));
    }

    /**
     * This method handles an ConstraintViolationException by generating a customized error response.
     *
     * @param exception The ConstraintViolationException that is being handled.
     * @return A ResponseEntity object with status code 400 (Internal Server Error) and a body containing the customized error response.
     * @throws IllegalArgumentException if the com.bae.ii.exception parameter is null.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity
            .badRequest()
            .body(commonResponseBuilder.generateErrorResponse(exceptionHandlerUtils.getErrors(exception)));
    }

}
