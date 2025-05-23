package com.common.starter.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.starter.model.response.CommonErrorResponse;
import com.common.starter.model.response.ErrorResponse;
import com.common.starter.model.response.SuccessResponse;
import static com.common.starter.util.CommonResponseConstants.ERROR_RESPONSE_CODE;
import static com.common.starter.util.CommonResponseConstants.ERROR_RESPONSE_MESSAGE;
import static com.common.starter.util.CommonResponseConstants.SUCCEED_RESPONSE_CODE;
import static com.common.starter.util.CommonResponseConstants.SUCCEED_RESPONSE_MESSAGE;

@Component
public class CommonResponseBuilder {

    /**
     * Generates an error response object with the provided list of errors.
     *
     * @param errors The list of errors.
     * @return The ErrorResponse object.
     */
    public ErrorResponse generateErrorResponse(List<CommonErrorResponse> errors) {
        return ErrorResponse.builder()
            .code(ERROR_RESPONSE_CODE)
            .message(ERROR_RESPONSE_MESSAGE)
            .errors(errors)
            .build();
    }

    /**
     * Generate a success message.
     *
     * @return SuccessResponse object
     */
    public SuccessResponse generateSuccessResponse() {
        return SuccessResponse.builder()
            .code(SUCCEED_RESPONSE_CODE)
            .message(SUCCEED_RESPONSE_MESSAGE)
            .build();
    }

}
