package com.common.starter.model.enums;

/**
 * Represents error code info.
 */
public interface ErrorCode {

    String getCode();

    String getMessage();

    static ErrorCode from(final String code, final String message) {
        return new ErrorCode() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

}
