package com.common.starter.util;

import com.common.starter.exception.application.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The XmlConverter class provides a method to convert a given input xml string to special class object.
 */
@Slf4j
@RequiredArgsConstructor
public class XmlConversionUtils {

    /**
     * XmlMapper.
     */
    private final XmlMapper mapper;

    /**
     * Convert xml to clazz object.
     *
     * @param xml xml string
     * @param clazz class to transform to
     * @param <T> transform to type
     * @return clazz object
     */
    public <T> T deserialize(String xml, Class<T> clazz) {
        try {
            return mapper.readValue(xml, clazz);
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);

            throw new SerializingException(e.getMessage());
        }
    }

}
