package com.common.starter.handler;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;

/**
 * A class that is responsible for handling correlation IDs.
 */
@Component
@AllArgsConstructor
public class CorrelationIDHandler {

    /**
     * Tracer.
     */
    private final Tracer tracer;

    /**
     * Retrieves the correlation ID associated with the current trace.
     *
     * @return The correlation ID as a string. Returns an empty string if no correlation ID is found.
     */
    public String getCorrelationId() {
        return Optional.of(tracer)
            .map(Tracer::currentTraceContext)
            .map(CurrentTraceContext::context)
            .map(TraceContext::traceId)
            .orElse("");
    }

}
