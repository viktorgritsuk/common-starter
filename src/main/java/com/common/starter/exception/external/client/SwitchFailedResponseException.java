package com.common.starter.exception.external.client;

import java.util.List;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ExternalSystemEnum;

/**
 * SwitchFailedResponseException is a RuntimeException subclass that represents failed SWITCH response.
 * <p>
 * This exception is used to handle errors encountered during the execution of database stored procedures.
 * It contains a list of Error objects that represent the errors that occurred.
 */
public class SwitchFailedResponseException extends ClientExternalErrorException {

    /**
     * Creates an exception.
     *
     * @param errors - list of BPM errors
     */
    public SwitchFailedResponseException(final List<CommonError> errors) {
        super(errors, ExternalSystemEnum.SWITCH);
    }

    /**
     * Creates an exception.
     *
     * @param error - BPM errors
     */
    public SwitchFailedResponseException(final CommonError error) {
        super(error, ExternalSystemEnum.SWITCH);
    }

}

