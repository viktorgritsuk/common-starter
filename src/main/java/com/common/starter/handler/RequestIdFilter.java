package com.common.starter.handler;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Adds uniqiue ID for each request in logs.
 */
@Component
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class RequestIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        MDC.put("requestId", UUID.randomUUID().toString());

        filterChain.doFilter(request, response);

        MDC.remove("requestId");
    }

}
