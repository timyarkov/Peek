package com.timyarkov.peek.model.comms.driver;

import com.timyarkov.peek.model.comms.util.CommsResponse;

/**
 * Interface for communications to be done by.
 */
@FunctionalInterface
public interface CommsDriverStrategy {
    /**
     * Gets data via communications.
     * @return Data from request; if failed, failed variable will be set in the response.
     */
    public CommsResponse getData();
}
