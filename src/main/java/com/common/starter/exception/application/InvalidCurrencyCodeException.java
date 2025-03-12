package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;

/**
 * Exception thrown when an invalid currency code is encountered.
 */
public class InvalidCurrencyCodeException extends ApplicationException {

    /**
     * Constructs a new InvalidCurrencyCodeException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidCurrencyCodeException(String message) {
        super(message);
    }

    /**
     * Retrieves the error code associated with this exception.
     *
     * @return The error code for the invalid currency code exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_CURRENCY_CODE_EXCEPTION;
    }
}
