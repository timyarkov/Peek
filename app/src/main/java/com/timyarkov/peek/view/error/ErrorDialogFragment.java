package com.timyarkov.peek.view.error;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class ErrorDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Uh oh!")
                .setMessage("An error occurred; Details:\n" + getArguments().getString("error"))
                .setPositiveButton("Ok", (d, id) -> {
                    // Do nothing we just want to let the user know
                });

        return builder.create();
    }
}
