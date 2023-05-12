package com.example.final_todo.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.final_todo.R;
import com.example.final_todo.model.Task;

public class AddTaskDialog extends AppCompatDialogFragment {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private AddTaskDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_task, null);

        titleEditText = view.findViewById(R.id.edit_text_title);
        descriptionEditText = view.findViewById(R.id.edit_text_description);

        builder.setView(view)
                .setTitle("Add Task")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = titleEditText.getText().toString().trim();
                        String description = descriptionEditText.getText().toString().trim();

                        if (!title.isEmpty()) {
                            Task task = new Task(title, description, "", ""); // Provide default values for date and time
                            listener.onTaskAdded(task);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the dialog
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddTaskDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment().getClass().getName()
                    + " must implement AddTaskDialogListener");
        }
    }

    public interface AddTaskDialogListener {
        void onTaskAdded(Task task);
    }
}
