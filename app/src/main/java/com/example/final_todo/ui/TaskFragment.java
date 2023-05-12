import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_todo.R;
import com.example.final_todo.model.Task;
import com.example.final_todo.viewmodel.TaskViewModel;

import java.util.List;

public class TaskFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private TaskViewModel taskViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);

        // Initialize the TaskViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Observe the task list from the ViewModel
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                // Update the UI with the new list of tasks
                taskAdapter.setTaskList(tasks);
            }
        });

        return view;
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private List<Task> taskList;

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task task = taskList.get(position);
            holder.titleTextView.setText(task.getTitle());
            holder.descriptionTextView.setText(task.getDescription());
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        public void setTaskList(List<Task> taskList) {
            this.taskList = taskList;
            notifyDataSetChanged();
        }

        class TaskViewHolder extends RecyclerView.ViewHolder {

            private TextView titleTextView;
            private TextView descriptionTextView;

            TaskViewHolder(@NonNull View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.title_text_view);
                descriptionTextView = itemView.findViewById(R.id.description_text_view);
            }
        }
    }
}
