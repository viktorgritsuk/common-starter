package com.common.starter.conversion;

import org.springframework.stereotype.Component;

import com.common.starter.model.db.FunctionError;
import com.common.starter.model.domain.CommonError;

@Component
public class FunctionResponseToCommonErrorConverter {

    /**
     * Converts db function error in domain error.
     * @param source db function error
     * @return domain error
     */
    public CommonError convert(final FunctionError source) {
        return CommonError.builder()
            .code(source.errorCode())
            .message(source.description())
            .build();
    }

}
