package com.example.final_todo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_todo.R;
import com.example.final_todo.model.Task;

import com.example.final_todo.ui.TaskAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy task data
        taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", "Description 1"));
        taskList.add(new Task("Task 2", "Description 2"));
        taskList.add(new Task("Task 3", "Description 3"));

        // Initialize the TaskAdapter
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(taskAdapter);

        // Add a new task
        Task newTask = new Task("New Task", "New Description");
        addTask(newTask);
    }

    private void addTask(Task task) {
        taskList.add(task);
        taskAdapter.notifyItemInserted(taskList.size() - 1);
    }

    @Override
    public void onEditClick(Task task) {
        // Handle edit click
    }

    @Override
    public void onDeleteClick(Task task) {
        // Handle delete click
    }
}
