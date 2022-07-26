package com.timyarkov.peek.model.comms.util.parser;

import com.timyarkov.peek.model.comms.util.CommsResponse;
import com.timyarkov.peek.model.items.Post;

/**
 * Parses responses into items the system can use.
 */
public interface ResponseParser {
    //!!TODO cohesion kinda eh??? group with driver maybe

    /**
     * Parses a comms response into a post.
     * @param response Response to parse.
     * @param index Index of item to parse (0 based, assuming responses include multiple
     *              items). If responses only include one parseable item, value is to be ignored.
     * @return Parsed Post.
     */
    public Post parseResponse(CommsResponse response, int index);
}
