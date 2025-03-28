package com.common.starter.model.db;

import java.io.Serializable;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record FunctionError(

    @JacksonXmlProperty(localName = "ERROR_CODE")
    String errorCode,

    @JacksonXmlProperty
    @JsonAlias({"ERROR_DESCRIPTION", "ERROR_DESC"})
    String description

) implements Serializable {

    @Override
    public String toString() {
        return new StringJoiner(", ", FunctionError.class.getSimpleName() + "[", "]")
            .add("errorCode='" + errorCode + "'")
            .add("description='" + description + "'")
            .toString();
    }

}
