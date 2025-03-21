package com.common.starter.model.domain;

import java.io.Serializable;
import java.util.StringJoiner;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

/**
 * Represents error.
 */
@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public record CommonError(

    String code,
    String message

) implements Serializable {

    @Override
    public String toString() {
        return new StringJoiner(", ", CommonError.class.getSimpleName() + " { ", " }")
            .add("code='" + code + "'")
            .add("message='" + message + "'")
            .toString();
    }

}
