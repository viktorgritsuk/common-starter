package com.common.starter.health;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * Utility class which contains a method for checking connection to a target system.
 */
public final class ConnectionChecker {

    private ConnectionChecker() {
    }

    /**
     * This method checks connection to target service.
     * @param url - URL of the target service
     * @param connectionTimeout - connection timeout. If service doesn't response at this time - service is unavailable
     * @return - true - service available, false - service unavailable
     */
    public static boolean isConnected(String url, int connectionTimeout) throws IOException, URISyntaxException {
        boolean status = false;
        URL serviceUrl = new URI(url).toURL();
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();

        connection.setRequestMethod(HttpMethod.GET.name());
        connection.setConnectTimeout(connectionTimeout);

        connection.connect();

        if (connection.getResponseCode() == HttpStatus.OK.value()) {
            status = true;
        }

        return status;
    }

}
