package com.common.starter.model.db;

import java.io.Serializable;
import java.util.StringJoiner;

import com.common.starter.model.enums.FunctionResponseHeaderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "BAE_HEADER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionResponseHeader implements Serializable {

    /**
     * Represents the STAT property in the AccountListResponseHeader class.
     * <p>
     * The STAT property is used to store the status of the response header.
     * It is annotated with @JacksonXmlProperty to specify the XML element name "STAT" during XML serialization and deserialization.
     * <p>
     * Example usage:
     * AccountListResponseHeader header = new AccountListResponseHeader();
     * header.setStat("SUCCESS");
     * String stat = header.getStat(); // returns "SUCCESS"
     */
    @JacksonXmlProperty(localName = "STAT")
    private FunctionResponseHeaderStatus stat;

    @Override
    public String toString() {
        return new StringJoiner(", ", FunctionResponseHeader.class.getSimpleName() + "[", "]")
            .add("stat='" + stat + "'")
            .toString();
    }

}
