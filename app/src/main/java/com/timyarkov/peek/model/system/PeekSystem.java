package com.timyarkov.peek.model.system;

import com.timyarkov.peek.model.items.Post;

import java.util.List;

/**
 * Facade to interact with the backend system.
 */
public interface PeekSystem {
    // System State and Control
    /**
     * Gets the system's error state. Null if no current error.
     * @return Current system error state.
     */
    public String getCurrentError();

    /**
     * Gracefully shuts down the system, committing any required data
     * to files and such.
     */
    public void shutdown();

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
     * "Borrows" the remaining posts for the day; that is, gets the
     * number, then sets number internally to 0.
     * @return Number of remaining posts for the day.
     */
    public int borrowPeeks();

    /**
     * "Returns" the remaining posts for the day; that is, adds back
     * any remaining posts to the internal counter. To be called on
     * application exit.
     */
    public void returnPeeks(int returned);

    /**
     * Gets a random assortment of however many posts are available for the day
     * based on the set preferences.
     * @return Posts to display.
     */
    public List<Post> getPosts();
}
