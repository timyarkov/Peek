package com.timyarkov.peek.model.comms.driver;

import com.timyarkov.peek.model.comms.util.CommsResponse;
import com.timyarkov.peek.model.env.Environment;

import java.io.*;
import java.net.*;

public class ImgurCommsDriver implements CommsDriverStrategy {
    private static final String IMGUR_URL = "https://api.imgur.com/3/gallery/search/?q=";
    private static final String IMGUR_API_VAR = "IMGUR_API_CLIENT";

    private String query;

    /**
     * Creates a comms driver for making the query to Imgur.
     * @param query Query to make.
     */
    public ImgurCommsDriver(String query) {
        this.query = query;
    }

    /**
     * Gets data from Imgur for the given query.
     * @return Data from request; if failed, failed variable will be set in the response.
     */
    @Override
    public CommsResponse getData() {
        try {
            // Credit: https://www.journaldev.com/7148/java-httpurlconnection-example-java-http-request-get-post
            URL url = new URL(IMGUR_URL + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Client-ID " +
                                            Environment.getEnv(IMGUR_API_VAR));

            BufferedReader in = new BufferedReader(new InputStreamReader(
                                                    connection.getInputStream()));
            String data;
            StringBuffer res = new StringBuffer();

            while ((data = in.readLine()) != null) {
                res.append(data);
            }

            in.close();

            return new CommsResponse(false, connection.getResponseCode(), res.toString());
        } catch (IOException e) {
            return new CommsResponse(true, -1,
                                     "Exception: " + e.getMessage());
        }
    }
}
