package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;

/**
 * Represents an exception that occurs when there is an invalid response from Database.
 */
public class InvalidDatabaseResponseException extends ApplicationException {

    /**
     * Retrieves the error code associated with the exception.
     *
     * @return The ErrorCode representing the error code of the exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_DATABASE_RESPONSE_EXCEPTION;
    }

}
