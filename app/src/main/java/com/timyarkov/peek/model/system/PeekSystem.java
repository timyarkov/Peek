package com.timyarkov.peek.model.system;

import com.timyarkov.peek.model.items.Post;

import java.util.List;

/**
 * Facade to interact with the backend system.
 */
public interface PeekSystem {
    // System State
    /**
     * Gets the system's error state. Null if no current error.
     * @return Current system error state.
     */
    public String getCurrentError();

    // System Observation
    /**
     * Adds a system observer. Cannot be null.
     * @param o Observer to add.
     * @return Whether successful or not.
     */
    public boolean addObserver(PeekSystemObserver o);

    /**
     * Removes a system observer.
     * @param o Observer to add.
     * @return Whether successful or not (i.e. whether an actual observer or not).
     */
    public boolean removeObserver(PeekSystemObserver o);

    // System Functionality
    /**
     * Gets the amount of posts remaining for the day.
     * @return Amount of posts remaining for the day.
     */
    public int getRemainingPosts();

    /**
     * Gets a random assortment of however many posts are available for the day
     * based on the set preferences.
     * @return Posts to display.
     */
    public List<Post> getPosts();
}
