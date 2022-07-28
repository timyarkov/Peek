package com.timyarkov.peek.view.main;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.timyarkov.peek.model.items.Post;
import com.timyarkov.peek.model.system.DummyPeekSystem;
import com.timyarkov.peek.model.system.PeekSystem;
import com.timyarkov.peek.model.system.PeekSystemObserver;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel implements PeekSystemObserver {
    private ExecutorService pool;

    private PeekSystem model;
    private MutableLiveData<String> currentError;
    private MutableLiveData<Integer> remainingPosts;
    private MutableLiveData<List<Post>> posts;

    public MainViewModel(@NonNull Application application) {
        super(application);
        // Set up backend model and thread pool
        this.model = new DummyPeekSystem(); //!!TODO switch out to realsies
        this.model.addObserver(this); //!!TODO probably need to enforce a singleton at some point for this

        this.pool = Executors.newFixedThreadPool(2, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        // Setup required live data
        this.currentError = new MutableLiveData<>();
        this.remainingPosts = new MutableLiveData<>(this.model.borrowPeeks()); // Borrow peeks!
        this.posts = new MutableLiveData<>();
    }

    // Backend System
    /**
     * Checks for what needs to be updated based on the model's current state.
     */
    @Override
    public void update() {
        this.currentError.setValue(this.model.getCurrentError());
    }

    /**
     * On VM clear, we also return peeks.
     */
    @Override
    public void onCleared() {
        super.onCleared();
        this.model.returnPeeks(this.remainingPosts.getValue());
    }

    // Error State
    /**
     * Sets the current error. Use null for no error.
     * @param error Error to set.
     */
    private void setCurrentError(String error) {
        this.currentError.setValue(error);
    }

    /**
     * Returns the current error state live data.
     * @return Current error state.
     */
    public LiveData<String> getCurrentError() {
        // Create if not existing yet
        if (currentError == null) {
            this.currentError = new MutableLiveData<>();
        }

        return this.currentError;
    }

    // Functionality
    /**
     * Gets the remaining posts live data (as was borrowed from the system).
     * @return Remaining posts.
     */
    public LiveData<Integer> getRemainingPosts() {
        return this.remainingPosts;
    }

    /**
     * Decrement the remaining post count by one.
     */
    public void usePeek() {
        this.remainingPosts.setValue(this.remainingPosts.getValue() - 1);
    }

    /**
     * Returns the live data object for posts. Does NOT set the value; see
     * updatePostsList() for that functionality.
     * @see MainViewModel#updatePostsList()
     * @return Post live data.
     */
    public LiveData<List<Post>> getPostsLiveData() {
        return this.posts;
    }

    /**
     * Updates the posts list; essentially the operation that actually "gets" the data.
     */
    public void updatePostsList() {
        // Create if not existing yet
        if (this.posts == null) {
            this.posts = new MutableLiveData<>();
        }

        this.pool.execute(() -> {
            List<Post> ret = this.model.getPosts();

            // Run on UI thread
            new Handler(Looper.getMainLooper()).post(() -> {
                this.posts.setValue(ret);
            });
        });
    }
}
