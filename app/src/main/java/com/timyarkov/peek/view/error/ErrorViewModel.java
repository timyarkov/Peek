package com.timyarkov.peek.view.error;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@Deprecated
public class ErrorViewModel extends AndroidViewModel {
    private MutableLiveData<String> error;

    public ErrorViewModel(@NonNull Application application) {
        super(application);

        // Setup live data
        this.error = new MutableLiveData<>("Error Message here!");
    }

    /**
     * Gets the live data for the error message.
     * @return Error Message live data.
     */
    public LiveData<String> getError() {
        return this.error;
    }

    /**
     * Sets the error message.
     * @param error Error message to set.
     */
    public void setError(String error) {
        this.error.setValue(error);
    }
}