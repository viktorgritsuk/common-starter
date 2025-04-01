package com.common.starter.health;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import static com.common.starter.health.ConnectionChecker.isConnected;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EndpointHealthIndicator implements HealthIndicator {

    /**
     * Connection timeout to FlexCube.
     */
    private static final int CONNECTION_TIMEOUT = 5000;

    /**
     * Endpoint URL to check.
     */
    private final String url;

    /**
     * Endpoint with available status message.
     */
    private final String endpointAvailableMessage;

    /**
     * Endpoint with unavailable status message.
     */
    private final String endpointUnavailableMessage;

    /**
     * Endpoint with unavailable log message.
     */
    private final String connectionErrorMessage;

    /**
     * Creates instance.
     * @param url endpoint url
     * @param endpointAvailableMessage endpoint with available status message
     * @param endpointUnavailableMessage endpoint with unavailable status message
     * @param connectionErrorMessage endpoint with unavailable log message
     */
    public EndpointHealthIndicator(
        String url,
        String endpointAvailableMessage,
        String endpointUnavailableMessage,
        String connectionErrorMessage
    ) {
        this.url = url;
        this.endpointAvailableMessage = endpointAvailableMessage;
        this.endpointUnavailableMessage = endpointUnavailableMessage;
        this.connectionErrorMessage = connectionErrorMessage + " with error: {}";
    }

    /**
     * Creates instance.
     * @param url endpoint url
     */
    public EndpointHealthIndicator(String url) {
        this.url = url;
        this.endpointAvailableMessage = "Endpoint is available on URL: " + url;
        this.endpointUnavailableMessage = "Endpoint is unavailable on URL: " + url;
        this.connectionErrorMessage = "Error connecting to endpoint by URL: " + url + " with error: {}";
    }

    @Override
    public Health health() {
        if (isEndpointIsAvailable()) {
            return Health.up()
                .withDetail(endpointAvailableMessage, url)
                .build();
        }
        else {
            return Health.outOfService()
                .withDetail(endpointUnavailableMessage, url)
                .build();
        }
    }

    /**
     * Checks whether the FlexCube Cash service is running and available.
     *
     * @return {@code true} if the FlexCube Cash service is running, otherwise {@code false}.
     */
    private boolean isEndpointIsAvailable() {
        boolean status = false;

        try {
            status = isConnected(url, CONNECTION_TIMEOUT);
        }
        catch (IOException | URISyntaxException e) {
            log.warn(connectionErrorMessage, e.getMessage());
        }

        return status;
    }

}
