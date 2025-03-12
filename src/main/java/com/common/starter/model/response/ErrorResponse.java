package com.common.starter.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import com.common.starter.model.domain.CommonError;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public record ErrorResponse(

    Integer code,
    String message,
    List<CommonError> errors

) implements Serializable {

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorResponse.class.getSimpleName() + "[", "]")
            .add("code=" + code)
            .add("message='" + message + "'")
            .add("errors=" + errors)
            .toString();
    }

}
