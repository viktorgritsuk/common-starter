package com.common.starter.conversion;

import java.util.StringJoiner;

import org.springframework.stereotype.Component;

import com.common.starter.model.enums.ErrorCodeEnum;
import com.common.starter.model.response.CommonErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

/**
 * Converter class to convert from MismatchedInputException to Error.
 * Using for convert json deserialization errors.
 */
@Component
public class RequestDeserializationFieldErrorConverter {

    /**
     * Converts an instance of MismatchedInputException to Error.
     *
     * @param exc The MismatchedInputException instance to be converted.
     * @return The converted Error instance.
     */
    public CommonErrorResponse convert(MismatchedInputException exc) {
        String fieldName = getFullFieldName(exc);

        return CommonErrorResponse.builder()
            .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
            .message(fieldName + " value is incorrect")
            .build();
    }

    /**
     * Generates full json field name from MismatchedInputException.
     *
     * @param exc {@link MismatchedInputException}
     * @return full json field name
     */
    private String getFullFieldName(MismatchedInputException exc) {
        StringJoiner stringJoiner = new StringJoiner(".");

        exc.getPath().forEach(reference -> {
            if (reference.getFieldName() != null) {
                stringJoiner.add(reference.getFieldName());
            }
            else {
                stringJoiner.add("[]");
            }
        });

        return stringJoiner.toString();
    }

}
