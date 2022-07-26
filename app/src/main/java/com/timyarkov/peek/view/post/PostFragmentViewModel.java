package com.timyarkov.peek.view.post;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.timyarkov.peek.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PostFragmentViewModel extends AndroidViewModel {
    private ExecutorService pool;
    private MutableLiveData<Bitmap> imageData;

    public PostFragmentViewModel(@NonNull Application application) {
        super(application);

        this.pool = Executors.newFixedThreadPool(2, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
    }

    /**
     * Returns the live data for images. Does NOT include the actual obtaining of data,
     * see updateImageData() for that.
     * @see PostFragmentViewModel#updateImageData(String)
     * @return Image live data.
     */
    public LiveData<Bitmap> getImageLiveData() {
        // Create if doesn't exist yet
        if (this.imageData == null) {
            this.imageData = new MutableLiveData<>();
        }

        return this.imageData;
    }

    /**
     * Updates the image data with the bitmap data downloaded from the internet.
     * @param url URL of image resource.
     */
    public void updateImageData(String url) {
        this.pool.execute(() -> {
            // Credit: https://stackoverflow.com/a/9159789
            Bitmap bm = null;

            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                bm = BitmapFactory.decodeResource(getApplication().getResources(),
                        R.drawable.resource_error); //!!TODO maybe something nicer...
            }

            Bitmap finalBm = bm;
            new Handler(Looper.getMainLooper()).post(() -> {
                this.imageData.setValue(finalBm);
            });
        });
    }
}
