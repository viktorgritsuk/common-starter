package com.common.starter.exception.external.client;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.common.starter.exception.external.ExternalErrorException;
import com.common.starter.model.domain.CommonError;
import com.common.starter.model.enums.ExternalSystem;

import lombok.Getter;

@Getter
public class ClientExternalErrorException extends ExternalErrorException {

    /**
     * External system.
     */
    private final transient ExternalSystem externalSystem;

    public ClientExternalErrorException(final List<CommonError> errors, ExternalSystem externalSystem) {
        super(HttpStatus.CONFLICT, errors);
        this.externalSystem = externalSystem;
    }

    public ClientExternalErrorException(final CommonError error, ExternalSystem externalSystem) {
        super(HttpStatus.CONFLICT, error);
        this.externalSystem = externalSystem;
    }

}
