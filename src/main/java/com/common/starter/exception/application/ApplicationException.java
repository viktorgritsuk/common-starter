package com.common.starter.exception.application;

import com.common.starter.model.enums.ErrorCode;

import lombok.AllArgsConstructor;


/**
 * This abstract class represents an application exception that can be thrown in the application.
 * It extends the RuntimeException class and provides an abstract method to get the error code.
 */
@AllArgsConstructor
public abstract class ApplicationException extends RuntimeException {

    /**
     * Constructs a new ApplicationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    protected ApplicationException(String message) {
        super(message);
    }

    /**
     * Retrieves the error code associated with an exception.
     *
     * @return The ErrorCode representing the error code of the exception.
     */
    public abstract ErrorCode getErrorCode();

}
