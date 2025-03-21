package com.common.starter.exception.application;


import com.common.starter.model.enums.ErrorCode;
import com.common.starter.model.enums.ErrorCodeEnum;

public class TranslateException extends ApplicationException {

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCodeEnum.TRANSLATE_EXCEPTION;
    }

}
