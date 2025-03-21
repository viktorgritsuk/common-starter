package com.common.starter.logging;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Adds unique ID for each request in logs.
 */
@Component
@Slf4j
public class RequestIdFilter extends OncePerRequestFilter {

    /**
     * MDC request id key.
     */
    private static final String REQUEST_ID_KEY = "requestId";

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        MDC.put(REQUEST_ID_KEY, UUID.randomUUID().toString());

        filterChain.doFilter(request, response);

        MDC.remove(REQUEST_ID_KEY);
    }

}
