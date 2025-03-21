package com.common.starter.handler;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.common.starter.exception.external.ExternalErrorException;
import com.common.starter.model.response.CommonErrorResponse;
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
public class CommonExceptionHandler {

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
    public final ResponseEntity<Object> handleInternalErrorException(final Exception exception) {
        log.error(exception.getMessage(), exception);

        List<CommonErrorResponse> errorsList = exceptionHandlerUtils.getInternalErrors(exception);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(commonResponseBuilder.generateErrorResponse(errorsList));
    }

    /**
     * This method handles an ExternalErrorException by generating a customized error response.
     *
     * @param exception The ExternalErrorException that is being handled.
     * @return A ResponseEntity object with status code 500 (Internal Server Error) and a body containing the customized error response.
     * @throws IllegalArgumentException if the exception parameter is null.
     */
    @ExceptionHandler(ExternalErrorException.class)
    public final ResponseEntity<ErrorResponse> handleExternalErrorException(final ExternalErrorException exception) {
        log.error(exception.getMessage(), exception);

        List<CommonErrorResponse> errorList = exceptionHandlerUtils.getExternalErrors(exception);

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
    public final ResponseEntity<Object> handleBadRequestException(final ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);

        List<CommonErrorResponse> errorList = exceptionHandlerUtils.getConstraintViolationsErrors(exception);

        return ResponseEntity
            .badRequest()
            .body(commonResponseBuilder.generateErrorResponse(errorList));
    }

}
