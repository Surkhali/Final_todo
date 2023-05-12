package com.example.final_todo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_todo.R;
import com.example.final_todo.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private OnItemClickListener listener;

    public TaskAdapter(List<Task> taskList, OnItemClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public interface OnItemClickListener {
        void onEditClick(Task task);
        void onDeleteClick(Task task);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView editImageView;
        private ImageView deleteImageView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            editImageView = itemView.findViewById(R.id.editImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
        }

        public void bind(Task task, OnItemClickListener listener) {
            titleTextView.setText(task.getTitle());
            descriptionTextView.setText(task.getDescription());

            editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditClick(task);
                }
            });

            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(task);
                }
            });
        }
    }
}
