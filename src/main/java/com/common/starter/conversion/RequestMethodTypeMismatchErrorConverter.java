package com.common.starter.conversion;

import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ErrorCode;

@Component
public class RequestMethodTypeMismatchErrorConverter {

    /**
     * Converts MethodArgumentTypeMismatchException to Error object.
     *
     * @param source MethodArgumentTypeMismatchException instance
     * @return Error object
     */
    public CommonError convert(MethodArgumentTypeMismatchException source) {
        final String fieldName = source.getPropertyName();

        return CommonError.builder()
            .code(ErrorCode.INVALID_REQUEST_EXCEPTION.getCode())
            .message(fieldName + " value is incorrect")
            .build();
    }
}
