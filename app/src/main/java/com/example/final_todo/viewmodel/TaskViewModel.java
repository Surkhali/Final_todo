package com.example.final_todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.final_todo.data.TaskRepository;
import com.example.final_todo.model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository taskRepository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public void insertTask(Task task) {
        taskRepository.insert(task);
    }

    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}
