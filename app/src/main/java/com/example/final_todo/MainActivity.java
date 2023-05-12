import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.final_todo.R;
import com.example.final_todo.model.Task;
import com.example.final_todo.ui.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private EditText titleEditText;
    private EditText descriptionEditText; // Added
    private EditText dateEditText; // Added
    private EditText timeEditText; // Added
    private Button addButton;
    private Button navigateButton; // Added

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the EditText fields and Add button
        titleEditText = findViewById(R.id.editTextTitle);
        descriptionEditText = findViewById(R.id.editTextDescription); // Added
        dateEditText = findViewById(R.id.editTextDate); // Added
        timeEditText = findViewById(R.id.editTextTime); // Added
        addButton = findViewById(R.id.buttonAddTask);
        navigateButton = findViewById(R.id.navigateButton); // Added

        // Create the task list and adapter
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(taskAdapter);

        // Set click listener for the Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        // Set click listener for the Navigate button
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTaskFragment();
            }
        });
    }

    private void addTask() {
        // Get the task details from the EditText fields
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();

        // Validate the task details
        if (title.isEmpty() || description.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter all task details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Task object
        Task task = new Task(title, description, date, time);

        // Add the task to the list and notify the adapter
        taskList.add(task);
        taskAdapter.notifyItemInserted(taskList.size() - 1);

        // Clear the EditText fields
        titleEditText.setText("");
        descriptionEditText.setText("");
        dateEditText.setText("");
        timeEditText.setText("");
    }

    private void navigateToTaskFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new TaskFragment());
        fragmentTransaction.commit();
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
