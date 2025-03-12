package com.common.starter.exception.external;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.common.starter.model.domain.CommonError;

/**
 * DbFailedResponseException is a RuntimeException subclass that represents failed database response.
 * <p>
 * This exception is used to handle errors encountered during the execution of database stored procedures.
 * It contains a list of Error objects that represent the errors that occurred.
 */
public class DbFailedResponseException extends ExternalErrorException {

    /**
     * Creates an exception.
     *
     * @param errors - list of database errors
     */
    public DbFailedResponseException(List<CommonError> errors) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errors);
    }
}
