package com.common.starter.model.response;

import java.io.Serializable;
import java.util.StringJoiner;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public record CommonErrorResponse(
    String code,
    String message
) implements Serializable {

    @Override
    public String toString() {
        return new StringJoiner(", ", CommonErrorResponse.class.getSimpleName() + "[", "]")
            .add("code='" + code + "'")
            .add("message='" + message + "'")
            .toString();
    }

}
