package com.common.starter.conversion;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.common.starter.model.enums.ErrorCodeEnum;
import com.common.starter.model.response.CommonErrorResponse;

import jakarta.validation.ConstraintViolation;


/**
 * Converter class to convert from MismatchedInputException to Error.
 * Using for convert request validation errors.
 */
@Component
public class RequestValidationErrorConverter {

    /**
     * Converts an instance of ConstraintViolation to Error.
     *
     * @param constraintViolation The ConstraintViolation instance to be converted.
     * @return The converted Error instance.
     */
    public CommonErrorResponse convert(final ConstraintViolation<?> constraintViolation) {
        String excMessage = constraintViolation.getMessage();
        String fieldName = ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName();
        String errorMessage = fieldName + ": " + excMessage;

        return CommonErrorResponse.builder()
            .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
            .message(errorMessage)
            .build();
    }

    /**
     * Converts an instance of ObjectError to Error.
     *
     * @param error The MismatchedInputException instance to be converted.
     * @return The converted Error instance.
     */
    public CommonErrorResponse convert(final ObjectError error) {
        if (error instanceof FieldError fieldError) {
            String fieldName = capitalizeEachJsonFieldName(fieldError.getField());

            return CommonErrorResponse.builder()
                .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
                .message(fieldName + ": " + fieldError.getDefaultMessage())
                .build();
        }
        else {
            return CommonErrorResponse.builder()
                .code(ErrorCodeEnum.INVALID_REQUEST_EXCEPTION.getCode())
                .message(error.getDefaultMessage())
                .build();
        }
    }

    /**
     * Capitalize each word in the full json field name.
     *
     * @param fieldName full json field name
     * @return reformated field name;
     */
    private String capitalizeEachJsonFieldName(final String fieldName) {
        if (fieldName.isBlank()) {
            return fieldName;
        }

        char[] chars = fieldName.toCharArray();

        chars[0] = Character.toUpperCase(chars[0]);

        boolean dotFound = false;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '.') {
                dotFound = true;
            }
            else if (dotFound && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                dotFound = false;
            }
        }

        return String.valueOf(chars);
    }

}
