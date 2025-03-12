package com.common.starter.model.enums;

/**
 * Database responses headers statuses.
 */
public enum FunctionResponseHeaderStatus {

    /**
     * Success db response status.
     */
    SUCCESS,
    /**
     * Failed db response status.
     */
    FAILURE;


    public boolean isSucceed() {
        return this.equals(SUCCESS);
    }

}
