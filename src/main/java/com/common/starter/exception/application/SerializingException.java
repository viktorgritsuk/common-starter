package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

/**
 * Exception thrown when an error occurs during serialization.
 */
public class SerializingException extends ApplicationException {

    /**
     * Constructs a new SerializingException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SerializingException(String message) {
        super(message);
    }

    /**
     * Retrieves the error code associated with this exception.
     *
     * @return The error code for the serialization exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.SERIALIZING_EXCEPTION;
    }

}
