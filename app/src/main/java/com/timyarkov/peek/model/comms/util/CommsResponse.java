package com.timyarkov.peek.model.comms.util;

/**
 * POJO to use for communications responses.
 */
public class CommsResponse {
    //!!TODO any other data that may be needed?

    private boolean failed;

    private int responseCode;
    private String responseData;

    public CommsResponse(boolean failed, int responseCode, String responseData) {
        this.failed = failed;
        this.responseCode = responseCode;
        this.responseData = responseData;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseData() {
        return responseData;
    }

    public boolean hasFailed() {
        return failed;
    }
}
