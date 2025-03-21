package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

/**
 * Exception thrown when an invalid request is made.
 * This exception extends the {@link ApplicationException} class.
 */
public class InvalidRequestException extends ApplicationException {

    /**
     * Constructs a new InvalidRequestException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidRequestException(String message) {
        super(message);
    }

    /**
     * Returns the error code associated with this exception.
     *
     * @return the error code {@link ErrorCodeEnum#INVALID_REQUEST_EXCEPTION}.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.INVALID_REQUEST_EXCEPTION;
    }

}
