package com.common.starter.handler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface representing a logging com.bae.ii.service.
 * The LoggingService interface provides a method to display response data.
 */
public interface LoggingService {

    /**
     * Displays response data.
     *
     * @param request the HttpServletRequest object representing the client's request
     * @param response the HttpServletResponse object representing the server's response
     * @param body the response body object to be displayed
     */
    void displayResp(HttpServletRequest request, HttpServletResponse response, Object body);

}
