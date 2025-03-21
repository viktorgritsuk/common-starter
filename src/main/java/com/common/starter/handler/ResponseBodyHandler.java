package com.common.starter.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    /**
     * The logging service used for displaying response data.
     */
    private final LoggingService loggingService;

    @Override
    public boolean supports(
        @NotNull MethodParameter returnType,
        @NotNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
        Object body,
        @NotNull MethodParameter returnType,
        @NotNull MediaType selectedContentType,
        @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
        @NotNull ServerHttpRequest request,
        @NotNull ServerHttpResponse response
    ) {
        loggingService.displayResp(
            ((ServletServerHttpRequest) request).getServletRequest(),
            ((ServletServerHttpResponse) response).getServletResponse(),
            body
        );

        return body;
    }

}
