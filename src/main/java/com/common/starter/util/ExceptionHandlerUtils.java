package com.common.starter.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.common.starter.conversion.CommonErrorToResponseConverter;
import com.common.starter.conversion.RequestValidationErrorConverter;
import com.common.starter.exception.application.ApplicationException;
import com.common.starter.exception.external.ExternalErrorException;
import com.common.starter.exception.external.client.ClientExternalErrorException;
import com.common.starter.model.enums.ErrorCodeEnum;
import com.common.starter.model.response.CommonErrorResponse;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class ExceptionHandlerUtils {

    /**
     * RequestValidationErrorConverter.
     */
    private final RequestValidationErrorConverter requestValidationErrorConverter;

    /**
     * CommonErrorToResponseConverter.
     */
    private final CommonErrorToResponseConverter commonErrorToResponseConverter;

    /**
     * Returns a list of Error objects based on the provided com.bae.ii.exception.
     * <p>
     * If the com.bae.ii.exception is an ApplicationException, it creates an Error object using the error code and message from the com.bae.ii.exception's ErrorCode. If the com.bae.ii.exception is not an ApplicationException
     * , it creates an Error object using the internal server error code and message.
     * <p>
     *
     * @param exception The com.bae.ii.exception for which to generate the Error.
     * @return A list of Error objects.
     */
    public List<CommonErrorResponse> getInternalErrors(Exception exception) {
        return switch (exception) {
            case ApplicationException applicationException -> createApplicationExcErrorList(applicationException);
            case null, default -> createDefaultExcErrorList();
        };
    }

    private List<CommonErrorResponse> createApplicationExcErrorList(final ApplicationException exc) {
        return List.of(CommonErrorResponse.builder()
            .code(exc.getErrorCode().getCode())
            .message(StringUtils.isBlank(exc.getMessage())
                ? exc.getErrorCode().getMessage()
                : exc.getMessage())
            .build());
    }

    /**
     * Returns list of CommonError object generated from ConstraintViolationException object.
     * @param exc ConstraintViolationException exception
     * @return list of errors
     */
    public List<CommonErrorResponse> getConstraintViolationsErrors(final ConstraintViolationException exc) {
        return exc.getConstraintViolations().stream()
            .map(requestValidationErrorConverter::convert)
            .toList();
    }

    private List<CommonErrorResponse> createDefaultExcErrorList() {
        return List.of(CommonErrorResponse.builder()
            .code(ErrorCodeEnum.INTERNAL_SERVER.getCode())
            .message(ErrorCodeEnum.INTERNAL_SERVER.getMessage())
            .build());
    }

    /**
     * Returns refactored list of error from external error exception. Adds prefix to
     * error message recording external system.
     * @param exc external error exception
     * @return list of errors
     */
    public List<CommonErrorResponse> getExternalErrors(final ExternalErrorException exc) {
        return switch (exc) {
            case ClientExternalErrorException clientExternalErrorException -> getClientExternalErrors(clientExternalErrorException);
            default -> getClientExternalErrors(exc);
        };
    }

    /**
     * Returns refactored list of error from external error exception. Adds prefix to
     * error message recording external system.
     * @param exc external error exception
     * @return list of errors
     */
    public List<CommonErrorResponse> getClientExternalErrors(final ExternalErrorException exc) {
        return exc.getErrors().stream()
            .map(error -> CommonErrorResponse.builder()
                .code(error.code())
                .message(error.message())
                .build())
            .toList();
    }

    /**
     * Returns refactored list of error from client external error exception. Adds prefix to
     * error message recording external system.
     * @param exc client external error exception
     * @return list of errors
     */
    public List<CommonErrorResponse> getClientExternalErrors(final ClientExternalErrorException exc) {
        return exc.getErrors().stream()
            .map(error -> CommonErrorResponse.builder()
                .code(error.code())
                .message(addPrefixToErrorMessage(exc.getExternalSystem().getSystemName(), error.message()))
                .build())
            .toList();
    }

    private String addPrefixToErrorMessage(final String prefix, String message) {
        return prefix + ": " + message;
    }

}

