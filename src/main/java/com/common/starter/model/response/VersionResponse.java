package com.common.starter.model.response;

import java.io.Serializable;
import java.util.StringJoiner;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public record VersionResponse(
    String appVersion
) implements Serializable {

    @Override
    public String toString() {
        return new StringJoiner(", ", VersionResponse.class.getSimpleName() + "[", "]")
            .add("version='" + appVersion + "'")
            .toString();
    }

}
