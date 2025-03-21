package com.common.starter.exception.application;

import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

public class EncryptionException extends ApplicationException {


    /**
     * Create exception object.
     * @param message error message
     */
    public EncryptionException(String message) {
        super(message);
    }

    /**
     * Retrieves the error code associated with the com.bae.ii.exception.
     *
     * @return The ErrorCode representing the error code of the com.bae.ii.exception.
     */
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.ENCRYPT_EXCEPTION;
    }

}
