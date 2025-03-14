package com.common.starter;

import java.math.BigDecimal;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.common.starter.logging.RequestIdFilter;
import com.common.starter.logging.RequestLoggingFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;

@Configuration
public class BeanConfig {

    /**
     * Max length of payload.
     */
    private static final Integer MAX_PAYLOAD_CHARACTERS = 10000;

    /**
     * This method creates and configures a FilterRegistrationBean for the RequestIdFilter.
     *
     * @return FilterRegistrationBean<RequestIdFilter> - The configured FilterRegistrationBean.
     */
    @Bean
    public FilterRegistrationBean<RequestIdFilter> requestIdFilterFilter() {
        FilterRegistrationBean<RequestIdFilter> requestIdFilterBean = new FilterRegistrationBean<>();

        requestIdFilterBean.setFilter(new RequestIdFilter());
        requestIdFilterBean.setOrder(2);

        return requestIdFilterBean;
    }

    /**
     * Create Jackson2ObjectMapperBuilder bean.
     *
     * @return {@link Jackson2ObjectMapperBuilder}
     */
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
            .featuresToDisable(MapperFeature.ALLOW_COERCION_OF_SCALARS)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .postConfigurer(mapper -> {
                mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);

                mapper.coercionConfigFor(BigDecimal.class)
                    .setCoercion(CoercionInputShape.String, CoercionAction.TryConvert);

                mapper.coercionConfigFor(BigDecimal.class)
                    .setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsNull);

                mapper.coercionConfigFor(Boolean.class)
                    .setCoercion(CoercionInputShape.String, CoercionAction.TryConvert);

                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            });
    }

    /**
     * Creates and configures a CommonsRequestLoggingFilter bean.
     *
     * @return CommonsRequestLoggingFilter - The configured CommonsRequestLoggingFilter bean.
     */
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        RequestLoggingFilter filter = new RequestLoggingFilter();

        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(MAX_PAYLOAD_CHARACTERS);
        filter.setIncludeHeaders(false);

        return filter;
    }


}
