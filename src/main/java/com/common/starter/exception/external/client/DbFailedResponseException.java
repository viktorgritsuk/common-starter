package com.common.starter.exception.external.client;

import java.util.List;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ExternalSystemEnum;

/**
 * DbFailedResponseException is a RuntimeException subclass that represents failed database response.
 * <p>
 * This exception is used to handle errors encountered during the execution of database stored procedures.
 * It contains a list of Error objects that represent the errors that occurred.
 */
public class DbFailedResponseException extends ClientExternalErrorException {

    /**
     * Creates an exception.
     *
     * @param errors - list of database errors
     */
    public DbFailedResponseException(final List<CommonError> errors) {
        super(errors, ExternalSystemEnum.CORE);
    }

    /**
     * Creates an exception.
     *
     * @param error - database errors
     */
    public DbFailedResponseException(final CommonError error) {
        super(error, ExternalSystemEnum.CORE);
    }

}
