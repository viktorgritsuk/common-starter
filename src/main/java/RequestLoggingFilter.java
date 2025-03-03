import java.util.Set;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import jakarta.servlet.http.HttpServletRequest;

/**
 * RequestLoggingFilter is a custom filter class that extends the CommonsRequestLoggingFilter class.
 * It provides logging functionality for HTTP requests.
 */
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

    /**
     * Set of excluded URLs that should not be logged by the RequestLoggingFilter.
     */
    private final Set<String> excludedUrls = Set.of("/metrics/health/readiness", "/metrics/health/liveness", "/metrics", "/");

    /**
     * Determines whether a given HTTP request should be logged or not.
     *
     * @param request the HttpServletRequest object representing the incoming request
     * @return true if the request should be logged, false otherwise
     */
    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        if (excludedUrls.contains(request.getRequestURI())) {
            return false;
        }
        return logger.isDebugEnabled();
    }

}
