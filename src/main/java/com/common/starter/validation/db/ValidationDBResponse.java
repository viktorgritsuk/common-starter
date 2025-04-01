package com.common.starter.validation.db;

import java.util.List;

import com.common.starter.model.db.FunctionError;
import com.common.starter.model.db.FunctionResponseHeader;

/**
 * The {@code ValidationDBResponse} interface represents a generic database response that contains
 * a header and a list of errors. Classes implementing this interface are expected to provide
 * methods to retrieve the response header and any errors that occurred during the database operation.
 */
public interface ValidationDBResponse {

    /**
     * Retrieves the header of the database response.
     *
     * @return the {@link FunctionResponseHeader} of the response
     */
    FunctionResponseHeader getHeader();

    /**
     * Retrieves the list of errors associated with the database response.
     *
     * @return a {@link List} of {@link FunctionError} objects
     */
    List<FunctionError> getErrorList();

}
