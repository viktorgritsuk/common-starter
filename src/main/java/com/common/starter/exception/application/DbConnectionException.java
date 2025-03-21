package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

/**
 * Exception thrown when there is an error with the database connection.
 */
public class DbConnectionException extends ApplicationException {

    /**
     * Constructs a new SqlConnectionException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public DbConnectionException(String message) {
        super(message);
    }

    /**
     * Retrieves the error code associated with this exception.
     * This method overrides the getErrorCode method in the ApplicationException class.
     *
     * @return the error code representing a database connection error
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.DB_CONNECTION_EXCEPTION;
    }
}

