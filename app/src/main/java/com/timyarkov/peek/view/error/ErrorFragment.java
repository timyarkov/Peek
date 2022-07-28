package com.timyarkov.peek.view.error;

import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.timyarkov.peek.R;
import com.timyarkov.peek.view.post.PostFragmentViewModel;

import static android.view.View.GONE;

@Deprecated
public class ErrorFragment extends Fragment {
    private ErrorViewModel vm;
    private View rootView;

    // Utilities
    /**
     * Sets the error message to display.
     * @param error Error message to display.
     */
    public void setErrorMessage(String error) {
        if (error == null) {
            error = "NULL";
        }

        this.vm.setError(error);
    }

    // Main Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attach ViewModel
        this.vm = new ViewModelProvider(this).get(ErrorViewModel.class);

        // Error Message
        this.vm.getError().observe(this, newErrMsg -> {
            TextView errorBody = this.rootView.findViewById(R.id.errorBody);
            errorBody.setText(newErrMsg);
        });

        // Ok button
        this.rootView.findViewById(R.id.errorOkButton).setOnClickListener(view -> {
            this.rootView.setVisibility(GONE);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_error, container, false);
        this.rootView = v;

        return v;
    }

}