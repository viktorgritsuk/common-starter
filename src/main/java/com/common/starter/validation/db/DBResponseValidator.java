package com.common.starter.validation.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.starter.conversion.FunctionResponseToCommonErrorConverter;
import com.common.starter.exception.application.InvalidDatabaseResponseException;
import com.common.starter.exception.external.client.DbFailedResponseException;
import com.common.starter.exception.external.client.FlexCubeException;
import com.common.starter.model.db.FunctionError;
import com.common.starter.model.db.FunctionResponseHeader;

import lombok.RequiredArgsConstructor;

/**
 * The {@code DBResponseValidator} class is responsible for validating the database responses
 * and ensuring the integrity of the response by checking its status and any associated errors.
 */
@Component
@RequiredArgsConstructor
public class DBResponseValidator {

    private final FunctionResponseToCommonErrorConverter functionResponseToCommonErrorConverter;

    /**
     * Validates the given {@link ValidationDBResponse}.
     *
     * @param response the {@link ValidationDBResponse} to be validated
     * @throws InvalidDatabaseResponseException if the response header is null
     * @throws FlexCubeException if the response status indicates failure and contains error details
     */
    public void validateResponse(final ValidationDBResponse response) {
        FunctionResponseHeader header = response.getHeader();
        List<FunctionError> errorList = response.getErrorList();

        if (header == null) {
            throw new InvalidDatabaseResponseException();
        }

        if (!response.getHeader().getStat().isSucceed()) {
            throw new DbFailedResponseException(
                errorList
                    .stream()
                    .map(functionResponseToCommonErrorConverter::convert)
                    .toList());
        }
    }

}
