package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

/**
 * Represents an exception that occurs when there is an invalid response from FlexCube.
 */
public class InvalidResponseException extends ApplicationException {

    /**
     * Retrieves the error code associated with the exception.
     *
     * @return The ErrorCode representing the error code of the exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.INVALID_RESPONSE_EXCEPTION;
    }

}
