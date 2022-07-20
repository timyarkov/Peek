package com.timyarkov.peek.model.comms.driver;

import com.timyarkov.peek.model.comms.util.CommsResponse;
import com.timyarkov.peek.model.env.Environment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
            //!!TODO this brokey !
            /*
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest req = HttpRequest.newBuilder(new URI(IMGUR_URL + query)) //!!TODO sanitize?
                    .GET()
                    .setHeader("Authorization", "Client-ID " + Environment.getEnv(IMGUR_API_VAR))
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            */

            //!!TODO finish this ::////
            URL url = new URL(IMGUR_URL + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Client-ID " +
                                            Environment.getEnv(IMGUR_API_VAR));

            connection.connect();

            connection.getResponseCode();

            CommsResponse ret = new CommsResponse(false, connection.getResponseCode(), (String) connection.getContent());

            return new CommsResponse(false, res.statusCode(), res.body());
        } catch (IOException | InterruptedException | IllegalStateException e) {
            return new CommsResponse(true, -1,
                                     "Exception: " + e.getMessage());
        } catch (URISyntaxException e) {
            return new CommsResponse(true, -1,
                                     "Exception: " + e.getMessage());
        }
    }
}
