package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;

/**
 * Represents an exception that occurs when there is a connection issue to the FCUBS system.
 */
public class FCUBSConnectionException extends ApplicationException {

    /**
     * Retrieves the error code associated with the exception.
     *
     * @return The ErrorCode representing the error code of the exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.FCUBS_CONNECTION_EXCEPTION;
    }
}
