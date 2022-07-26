package com.timyarkov.peek.model.system;

/**
 * Observer of the system.
 */
public interface PeekSystemObserver {
    /**
     * Performs an operation based on the system broadcasting an update.
     */
    public void update();
}
