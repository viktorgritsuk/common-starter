package com.common.starter.util;

/**
 * Api response field values constants.
 */
public final class CommonResponseConstants {

    /**
     * Private constructor to hide public one.
     */
    private CommonResponseConstants() {
    }

    /**
     * Success code constant.
     */
    public static final Integer SUCCEED_RESPONSE_CODE = 0;

    /**
     * Success message constant.
     */
    public static final String SUCCEED_RESPONSE_MESSAGE = "Success";

    /**
     * Represents an error code.
     */
    public static final Integer ERRORED_RESPONSE_CODE = 1;

    /**
     * Represents an error message when an operation fails.
     */
    public static final String ERRORED_RESPONSE_MESSAGE = "Failure";


}
