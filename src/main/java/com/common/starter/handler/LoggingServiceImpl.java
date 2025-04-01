package com.common.starter.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {

    /**
     * Displays response data.
     *
     * @param request the HttpServletRequest object representing the client's request
     * @param response the HttpServletResponse object representing the server's response
     * @param body the response body object to be displayed
     */
    @Override
    public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder respMessage = new StringBuilder();
        Map<String, String> headers = getHeaders(response);

        respMessage.append("RESPONSE ");
        respMessage.append(" method = [").append(request.getMethod()).append("]");

        if (!headers.isEmpty()) {
            respMessage.append(" ResponseHeaders = [").append(headers).append("]");
        }

        respMessage.append(" responseBody = [").append(body).append("]");

        log.debug("response: {}", respMessage);
    }

    /**
     * Retrieves the headers from the HttpServletResponse object.
     *
     * @param response the HttpServletResponse object representing the server's response
     * @return a Map containing the headers and their corresponding values
     */
    private Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        Collection<String> headerMap = response.getHeaderNames();

        for (String str : headerMap) {
            headers.put(str, response.getHeader(str));
        }

        return headers;
    }

}
