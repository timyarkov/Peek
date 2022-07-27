package com.timyarkov.peek.view.main;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.lifecycle.ViewModelProvider;
import com.timyarkov.peek.R;
import com.timyarkov.peek.model.items.Post;
import com.timyarkov.peek.view.post.PostFragment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main Activity, involving the main card to interact with
 */
public class MainActivity extends AppCompatActivity {
    private MainViewModel vm;

    // Functionalities
    public void changePost() {
        List<Post> postPool = this.vm.getPostsLiveData().getValue();

        // Find index of current item, then rotate to next (wrap if done)
        PostFragment f = (PostFragment) getSupportFragmentManager().findFragmentById(R.id.post);
        int index = f.getCurrPost() == null ? 0 : postPool.indexOf(f.getCurrPost());

        f.displayPost(index == postPool.size() - 1 ? postPool.get(0) : postPool.get(++index));

        this.vm.usePeek();
    }

    // Main Methods
    @SuppressLint("ClickableViewAccessibility") //!!TODO accessibility important !
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        // Attach ViewModel
        this.vm = new ViewModelProvider(this).get(MainViewModel.class);

        // Remaining posts message
        this.vm.getRemainingPosts().observe(this, remaining -> {
            TextView notice = findViewById(R.id.mainHeader);
            notice.setText(String.format("You have %d remaining Peeks for today.", remaining));
        });

        // Post spring action + switch on pull down far enough
        SpringAnimation postAnim = new SpringAnimation(findViewById(R.id.post), DynamicAnimation.TRANSLATION_Y, 0);
        postAnim.getSpring().setDampingRatio(0.5f); // Value found from highly scientific tests

        final float[] initY = {-1};
        findViewById(R.id.post).setOnTouchListener((view, motionEvent) -> {
            if (postAnim.canSkipToEnd()) {
                postAnim.skipToEnd();
            }

            // If init pos not found yet set it
            if (initY[0] == -1 && motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                initY[0] = motionEvent.getRawY();
            }

            // Move based on diff of movement
            findViewById(R.id.post).setTranslationY(motionEvent.getRawY() - initY[0]);

            if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                // Switch next if far down enough
                if (findViewById(R.id.post).getTranslationY() >= findViewById(R.id.post).getHeight() / 4) {
                    this.changePost();
                }

                postAnim.start();// Anim to spring back
                initY[0] = -1; // Reset initial Y pos
            }

            return true;
        });

        // Get initial data
        this.vm.updatePostsList();
    }
}