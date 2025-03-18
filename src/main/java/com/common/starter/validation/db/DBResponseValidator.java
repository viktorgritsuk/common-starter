package com.common.starter.validation.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.starter.exception.application.InvalidDatabaseResponseException;
import com.common.starter.exception.external.FCUBSException;
import com.common.starter.model.db.FunctionError;
import com.common.starter.model.db.FunctionResponseHeader;
import com.common.starter.model.domain.CommonError;

/**
 * The {@code DBResponseValidator} class is responsible for validating the database responses
 * and ensuring the integrity of the response by checking its status and any associated errors.
 */
@Component
public class DBResponseValidator {

    /**
     * Validates the given {@link ValidationDBResponse}.
     *
     * @param response the {@link ValidationDBResponse} to be validated
     * @throws InvalidDatabaseResponseException if the response header is null
     * @throws FCUBSException if the response status indicates failure and contains error details
     */
    public void validateResponse(final ValidationDBResponse response) {
        FunctionResponseHeader header = response.getHeader();
        List<FunctionError> errorList = response.getErrorList();

        if (header == null) {
            throw new InvalidDatabaseResponseException();
        }

        if (!response.getHeader().getStat().isSucceed()) {
            throw new FCUBSException(
                errorList
                    .stream()
                    .map(o -> CommonError.builder().code(o.errorCode()).message(o.description()).build())
                    .toList());
        }

    }

}
