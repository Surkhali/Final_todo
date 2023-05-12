package com.example.final_todo.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.ColumnInfo;


import com.example.final_todo.model.Task;
import com.example.final_todo.data.TaskDao;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database")
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2) // Add the migration here
                    .build();
        }
        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Perform necessary database schema changes here, such as adding the "isCompleted" column
            database.execSQL("ALTER TABLE task_table ADD COLUMN isCompleted INTEGER NOT NULL DEFAULT 0");
        }
    };
}
