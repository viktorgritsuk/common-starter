package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

/**
 * This class represents an exception that is thrown when an unexpected response status is encountered.
 * It extends the ApplicationException class and overrides the getErrorCode method to return the error code
 * associated with the exception.
 */
public class UnexpectedResponseStatusException extends ApplicationException {

    /**
     * Retrieves the error code associated with the exception.
     *
     * @return The ErrorCode representing the error code of the exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.UNEXPECTED_RESPONSE_STATUS;
    }

}
