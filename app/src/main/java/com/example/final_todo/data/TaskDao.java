package com.example.final_todo.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.lifecycle.LiveData;

import com.example.final_todo.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table ORDER BY id DESC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    LiveData<Task> getTaskById(int taskId);

    @Query("SELECT COUNT(*) FROM task_table")
    LiveData<Integer> getTaskCount();

    @Query("SELECT * FROM task_table WHERE isCompleted = 0 ORDER BY id DESC")
    LiveData<List<Task>> getIncompleteTasks();

    @Query("SELECT * FROM task_table WHERE isCompleted = 1 ORDER BY id DESC")
    LiveData<List<Task>> getCompletedTasks();

    @Query("UPDATE task_table SET isCompleted = :isCompleted WHERE id = :taskId")
    void setTaskCompleted(int taskId, boolean isCompleted);

    @Query("SELECT * FROM tasks WHERE category = :category")
    LiveData<List<Task>> getTasksByCategory(String category);


    // Add more methods as per your application's requirements

}
