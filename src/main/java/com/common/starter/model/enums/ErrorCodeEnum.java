package com.common.starter.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration representing different error codes.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum implements ErrorCode {
    /**
     * Represents the internal server error.
     */
    INTERNAL_SERVER("1", "Internal server error"),

    /**
     * Enumeration representing different error codes.
     * <p>
     * This variable represents an unexpected response status error code.
     * <p>
     * The value of this variable is "2" and it corresponds to the message "Unexpected response status".
     */
    UNEXPECTED_RESPONSE_STATUS("2", "Unexpected response status"),

    /**
     * Enumeration representing different error codes.
     * <p>
     * This variable represents an FlexCube connection status error code.
     * <p>
     * The value of this variable is "3" and it corresponds to the message "onnection to FlexCube was refused".
     */
    FLEX_CUBE_CONNECTION_EXCEPTION("3", "Connection to FlexCube was refused"),

    /**
     * Enumeration representing different error codes.
     * <p>
     * This variable represents an invalid response status error code.
     * <p>
     * The value of this variable is "4" and it corresponds to the message "Invalid response from FlexCube".
     */
    INVALID_RESPONSE_EXCEPTION("4", "Invalid response from FlexCube"),

    INVALID_CURRENCY_CODE_EXCEPTION("5", "Invalid currency code"),

    /**
     * Represents an error code for a serializing processing error.
     * The value of this variable is "6" and it corresponds to the message "Serializing processing error".
     */
    SERIALIZING_EXCEPTION("6", "Serializing processing error "),

    /**
     * Represents an error code for an invalid response from the database.
     * The value of this variable is "7" and it corresponds to the message "Invalid response from database".
     */
    INVALID_DATABASE_RESPONSE_EXCEPTION("7", "Invalid response from database"),

    /**
     * Represents an error code for a database connection error.
     * The value of this variable is "8" and it corresponds to the message "Error with database connection".
     */
    DB_CONNECTION_EXCEPTION("8", "Error with database connection"),

    /**
     * Represents an error code for an error during translation.
     * The value of this variable is "9" and it corresponds to the message "Error during translating".
     */
    TRANSLATE_EXCEPTION("9", "Error during translating"),

    /**
     * Represents an error code for an invalid request.
     * <p>
     * The value of this variable is "10" and it corresponds to the message "Invalid request".
     */
    INVALID_REQUEST_EXCEPTION("10", "Invalid request"),

    /**
     * Represents an error code for a switch response with error.
     * <p>
     * The value of this variable is "11" and it corresponds to the message "Invalid response from SWITCH".
     */
    SWITCH_RESPONSE_WITH_ERRORS("11", "Invalid response from SWITCH"),

    /**
     * Represents an error code for a switch connection error.
     * <p>
     * The value of this variable is "12" and it corresponds to the message "Connection to SWITCH was refused".
     */
    SWITCH_CONNECTION_EXCEPTION("12", "Connection to SWITCH was refused"),

    /**
     * Represents an error code for a encryption process errors.
     * <p>
     * The value of this variable is "13" and it corresponds to the message "Encryption failed".
     */
    ENCRYPT_EXCEPTION("13", "Encryption failed"),

    /**
     * Represents a bad request error code.
     * Error code: "400"
     * Message: "Bad request"
     */
    BAD_REQUEST_EXCEPTION("400", "Bad request"),

    /**
     * Represents a resource not found error code.
     * Error code: "404"
     * Message: "Resource not found"
     */
    NO_RESOURCE_FOUND_EXCEPTION("404", "Resource not found");

    /**
     * Represents an error code.
     * <p>
     * This variable stores the error code value as a string.
     * It is used to identify different error conditions.
     */
    private final String code;
    /**
     * Represents the message associated with an error code.
     * <p>
     * This variable stores a string that describes the error message associated with an error code.
     * It provides additional information about the error condition.
     */
    private final String message;

}
