package com.timyarkov.peek.model.comms.manager;

import com.timyarkov.peek.model.comms.driver.CommsDriverStrategy;
import com.timyarkov.peek.model.comms.util.parser.ResponseParser;
import com.timyarkov.peek.model.items.Post;

import java.util.List;

/**
 * Manager for executing communications and returning results.
 */
public interface CommsManager {
    /**
     * Gets a list of posts based on the selected communications and parsing strategies.
     * @param comms Communications strategy to use.
     * @param parser Parsing strategy to use (should match correctly to comms strategy).
     * @return List of posts.
     */
    public List<Post> getPosts(CommsDriverStrategy comms, ResponseParser parser);
}
