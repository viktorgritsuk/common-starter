package com.common.starter.conversion;

import org.springframework.stereotype.Component;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.response.CommonErrorResponse;

@Component
public class CommonErrorToResponseConverter {

    /**
     * Converts domain error object into response error object.
     * @param source domain error object
     * @return response error object
     */
    public CommonErrorResponse convert(final CommonError source) {
        return CommonErrorResponse.builder()
            .code(source.code())
            .message(source.message())
            .build();
    }

}
