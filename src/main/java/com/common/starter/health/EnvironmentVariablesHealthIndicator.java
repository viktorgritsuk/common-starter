package com.common.starter.health;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * The {@code EnvironmentVariablesHealthIndicator} class is an implementation of the {@code HealthIndicator} interface
 * that checks if the required environment variables are defined.
 * This class is a component and can be used as a com.bae.ii.health indicator in a Spring Boot application.
 */
@Component
public class EnvironmentVariablesHealthIndicator implements HealthIndicator {

    /**
     * <p>
     * The undefinedVariables variable is an instance of List<String> that holds the names of environment variables
     * that are not defined. It is used in the EnvironmentVariablesHealthIndicator class to keep track of undefined variables.
     * </p>
     * <p>
     * The undefinedVariables variable is a private final variable, which means it cannot be modified once it is assigned a value.
     * It is initialized with an empty ArrayList of strings.
     * </p>
     * The undefinedVariables variable is updated in the isEnvironmentVariablesDefined() method of the EnvironmentVariablesHealthIndicator class.
     * This method checks if a list of environment variables is defined by checking their corresponding system properties.
     * If a variable is not defined, its name is added to the undefinedVariables list.
     * <p>
     * Example usage:
     * </p>
     * EnvironmentVariablesHealthIndicator healthIndicator = new EnvironmentVariablesHealthIndicator();
     */
    private final List<String> undefinedVariables = new ArrayList<>();

    /**
     * The {@code environmentVariables} variable is a private final variable of type List<String>.
     * It contains a list of environment variables that are required for the application to run properly.
     * <p>
     * The {@code environmentVariables} variable is initialized with the following environment variable names:
     * - REQUEST_TIMEOUT
     * - SERVER_PORT
     * - SWITCH_URL
     * - LOGGING_LEVEL
     * </p>
     * It is defined in the {@code EnvironmentVariablesHealthIndicator} class and used in the {@code isEnvironmentVariablesDefined} method
     * to check if these variables are defined by checking their corresponding system properties.
     */
    @Value("${required-environment-variables}")
    private List<String> environmentVariables;

    @Override
    public Health health() {
        if (isEnvironmentVariablesDefined()) {
            return Health.up()
                .build();
        }
        else {
            return Health.down()
                .withDetail("The following environment variables are not defined", undefinedVariables)
                .build();
        }
    }

    /**
     * Checks if the required environment variables are defined.
     *
     * @return true if all environment variables are defined, false otherwise
     */
    private boolean isEnvironmentVariablesDefined() {
        boolean status = false;

        environmentVariables
            .forEach(environmentVariable -> {
                String getenv = System.getenv(environmentVariable);
                if (getenv == null) {
                    undefinedVariables.add(environmentVariable);
                }
            });

        if (undefinedVariables.isEmpty()) {
            status = true;
        }

        return status;
    }

}
