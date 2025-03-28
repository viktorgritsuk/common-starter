package com.common.starter.handler;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.common.starter.conversion.RequestDeserializationFieldErrorConverter;
import com.common.starter.conversion.RequestMethodTypeMismatchErrorConverter;
import com.common.starter.conversion.RequestValidationErrorConverter;
import com.common.starter.model.enums.ErrorCodeEnum;
import com.common.starter.model.response.CommonErrorResponse;
import com.common.starter.model.response.ErrorResponse;
import com.common.starter.util.CommonResponseBuilder;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class ResponseEntityHandler extends ResponseEntityExceptionHandler {

    /**
     * CommonResponseBuilder.
     */
    private final CommonResponseBuilder commonResponseBuilder;

    /**
     * RequestValidationErrorConverter.
     */
    private final RequestValidationErrorConverter validationErrorConverter;

    /**
     * RequestDeserializationFieldErrorConverter.
     */
    private final RequestDeserializationFieldErrorConverter deserializationFieldErrorConverter;

    /**
     * RequestMethodTypeMismatchErrorConverter.
     */
    private final RequestMethodTypeMismatchErrorConverter methodTypeMismatchErrorConverter;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        @NotNull Exception ex,
        Object body,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode statusCode,
        @NotNull WebRequest request
    ) {
        log.error("Internal com.bae.ii.exception in the request: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = commonResponseBuilder.generateErrorResponse(List.of(
            CommonErrorResponse.builder()
                .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
                .message(ex.getMessage())
                .build()
        ));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        @NotNull MethodArgumentNotValidException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        log.error("Method argument com.bae.ii.exception in the request: {}", ex.getMessage(), ex);

        List<CommonErrorResponse> errors = ex.getBindingResult().getAllErrors().stream()
            .map(validationErrorConverter::convert)
            .toList();

        return ResponseEntity.badRequest().body(commonResponseBuilder.generateErrorResponse(errors));
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
        @NotNull NoResourceFoundException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        log.error("Exception in the request: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = commonResponseBuilder.generateErrorResponse(List.of(
            CommonErrorResponse.builder()
                .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
                .message(ex.getMessage())
                .build()
        ));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        @NotNull HttpMessageNotReadableException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        log.error("Http message not readable com.bae.ii.exception in the request: {}", ex.getMessage(), ex);

        CommonErrorResponse error;

        if (ex.getCause() instanceof MismatchedInputException mismatchedInputException) {
            error = deserializationFieldErrorConverter.convert(mismatchedInputException);
        }
        else {
            error = CommonErrorResponse.builder()
                .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
                .message(ex.getMessage())
                .build();
        }

        ErrorResponse errorResponse = commonResponseBuilder.generateErrorResponse(List.of(error));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
        @NotNull TypeMismatchException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        log.error("handle type mismatch: {}", ex.getMessage(), ex);

        ResponseEntity<Object> responseEntity;

        if (ex instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            CommonErrorResponse error = methodTypeMismatchErrorConverter.convert(methodArgumentTypeMismatchException);
            ErrorResponse errorResponse = commonResponseBuilder.generateErrorResponse(List.of(error));
            responseEntity = ResponseEntity.badRequest().body(errorResponse);
        }
        else {
            responseEntity = super.handleTypeMismatch(ex, headers, status, request);
        }

        return responseEntity;
    }

}
