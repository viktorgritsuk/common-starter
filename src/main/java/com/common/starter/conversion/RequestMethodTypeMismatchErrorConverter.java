package com.common.starter.conversion;

import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.common.starter.model.enums.ErrorCodeEnum;
import com.common.starter.model.response.CommonErrorResponse;

@Component
public class RequestMethodTypeMismatchErrorConverter {

    /**
     * Converts MethodArgumentTypeMismatchException to Error object.
     *
     * @param source MethodArgumentTypeMismatchException instance
     * @return Error object
     */
    public CommonErrorResponse convert(MethodArgumentTypeMismatchException source) {
        final String fieldName = source.getPropertyName();

        return CommonErrorResponse.builder()
            .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
            .message(fieldName + " value is incorrect")
            .build();
    }
}
