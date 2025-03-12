package com.common.starter.conversion;

import java.util.StringJoiner;

import org.springframework.stereotype.Component;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ErrorCode;
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
    public CommonError convert(MismatchedInputException exc) {
        String fieldName = getFullFieldName(exc);

        return CommonError.builder()
            .code(ErrorCode.INVALID_REQUEST_EXCEPTION.getCode())
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
