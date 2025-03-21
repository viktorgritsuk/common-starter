package com.common.starter.exception.external.client;

import java.util.List;

import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ExternalSystemEnum;

/**
 * FCUBSException is a RuntimeException subclass that represents an exception in FCUBS application.
 * <p>
 * This exception is used to handle errors encountered during the execution of FCUBS application.
 * It contains a list of Error objects that represent the errors that occurred.
 */
public class FlexCubeException extends ClientExternalErrorException {

    /**
     * Creates an exception.
     *
     * @param errors - list of FlexCube errors
     */
    public FlexCubeException(final List<CommonError> errors) {
        super(errors, ExternalSystemEnum.CORE);
    }

    /**
     * Creates an exception.
     *
     * @param error - FlexCube error
     */
    public FlexCubeException(final CommonError error) {
        super(error, ExternalSystemEnum.CORE);
    }


}

