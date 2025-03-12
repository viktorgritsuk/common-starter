package com.common.starter.exception.external;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.common.starter.model.domain.CommonError;

/**
 * FCUBSException is a RuntimeException subclass that represents an exception in FCUBS application.
 * <p>
 * This exception is used to handle errors encountered during the execution of FCUBS application.
 * It contains a list of Error objects that represent the errors that occurred.
 */
public class FCUBSException extends ExternalErrorException {

    /**
     * Creates an exception.
     *
     * @param errors - list of FlexCube errors
     */
    public FCUBSException(List<CommonError> errors) {
        super(HttpStatus.CONFLICT, errors);
    }

}

