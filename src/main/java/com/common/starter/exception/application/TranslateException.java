package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;

public class TranslateException extends ApplicationException {

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.TRANSLATE_EXCEPTION;
    }

}
