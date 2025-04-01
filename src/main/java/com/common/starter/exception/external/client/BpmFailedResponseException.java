package com.common.starter.exception.external.client;

import java.util.List;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ExternalSystemEnum;

/**
 * BpmFailedResponseException is a RuntimeException subclass that represents failed BPM response.
 * <p>
 * This exception is used to handle errors encountered during the execution of database stored procedures.
 * It contains a list of Error objects that represent the errors that occurred.
 */
public class BpmFailedResponseException extends ClientExternalErrorException {

    /**
     * Creates an exception.
     *
     * @param errors - list of BPM errors
     */
    public BpmFailedResponseException(final List<CommonError> errors) {
        super(errors, ExternalSystemEnum.BPM);
    }

    /**
     * Creates an exception.
     *
     * @param error - BPM errors
     */
    public BpmFailedResponseException(final CommonError error) {
        super(error, ExternalSystemEnum.BPM);
    }


}
