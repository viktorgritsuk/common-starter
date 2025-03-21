package com.common.starter.exception.external;

import java.util.List;

import org.springframework.http.HttpStatusCode;

import com.common.starter.model.domain.CommonError;

import lombok.Getter;

/**
 * ExternalErrorException is a RuntimeException subclass that represents an exception in external systems.
 * <p>
 * This exception is used to handle errors encountered during the execution of external system.
 * It contains a list of Error objects that represent the errors that occurred.
 */
@Getter
public class ExternalErrorException extends RuntimeException {

    /**
     * External response status code.
     */
    private final HttpStatusCode status;

    /**
     * A private variable that holds a list of Error objects.
     * <p>
     * The Error class represents an error, containing a code and a message.
     * Errors are stored in the List<Error> in the ExternalErrorException class.
     */
    private final List<CommonError> errors;

    public ExternalErrorException(
        final HttpStatusCode status,
        final List<CommonError> errors
    ) {
        this.status = status;
        this.errors = errors;
    }

    public ExternalErrorException(
        final HttpStatusCode status,
        final CommonError error
    ) {
        this(status, List.of(error));
    }

}
