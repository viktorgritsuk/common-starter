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

    /**
     * Represents the error code for a function error.
     * The errorCode field is used to identify the error code associated with a function error.
     * This field is annotated with @JacksonXmlProperty to specify that it should be mapped to the "ERROR_CODE" XML element during serialization and deserialization.
     */
    @JacksonXmlProperty(localName = "ERROR_CODE")
    String errorCode,

    /**
     * Represents the description of an error.
     * The description field is used to provide a detailed explanation of an error message.
     * This field is mapped to the "ERROR_DESCRIPTION" XML element during serialization and deserialization.
     */
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
