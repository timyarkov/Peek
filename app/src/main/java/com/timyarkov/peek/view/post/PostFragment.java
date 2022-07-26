package com.timyarkov.peek.view.post;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.timyarkov.peek.R;
import com.timyarkov.peek.model.items.Post;

public class PostFragment extends Fragment {
    private PostFragmentViewModel vm;
    private Post currPost;
    private View rootView;

    public PostFragment() {
        // Populate with dummy post
        /* TODO most likely deprecated...
        this.currPost = new Post("TITLE",
                                 PostDataType.TEXT,
                                 "Kangaroos are better than koalas, but it is very close.");
        */
    }

    /**
     * Returns the currently displayed post.
     * @return Current post.
     */
    public Post getCurrPost() {
        return this.currPost;
    }

    /**
     * Sets the post to be currPost, and then displays the contents.
     * @param post Post to display and set as currPost.
     */
    public void displayPost(Post post) {
        // Set
        this.currPost = post;

        // Display
        ConstraintLayout postBodyContainer = rootView.findViewById(R.id.postBodyContainer);
        postBodyContainer.removeAllViews(); // Clear out previous

        View item;

        switch (this.currPost.getType()) {
            case TEXT:
                TextView text = new TextView(postBodyContainer.getContext());
                text.setText(this.currPost.getData());
                item = text;
                break;
            case IMAGE:
                // Show loader
                this.rootView.findViewById(R.id.postLoadingSpinner)
                             .setVisibility(View.VISIBLE);

                ImageView img = new ImageView(postBodyContainer.getContext());
                this.vm.getImageLiveData().observe(this, imgData -> {
                    if (imgData != null) {
                        img.setImageBitmap(imgData);

                        // Hide loader
                        this.rootView.findViewById(R.id.postLoadingSpinner)
                                     .setVisibility(View.GONE);
                    }
                });
                this.vm.updateImageData(this.currPost.getData());

                item = img;
                break;
            case GIF: //!!TODO gif display
            case UNSUPPORTED:
            default:
                TextView errText = new TextView(postBodyContainer.getContext());
                errText.setText("Could not display this unsupported data: " +
                                this.currPost.getData());
                item = errText;
        }

        postBodyContainer.addView(item, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attach ViewModel
        this.vm = new ViewModelProvider(this).get(PostFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post, container, false);
        this.rootView = v;

        // Hide spinner at start
        this.rootView.findViewById(R.id.postLoadingSpinner).setVisibility(View.GONE);

        return v;
    }
}