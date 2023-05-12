import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_todo.R;
import com.example.final_todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);

        // Populate task list and update the adapter
        List<Task> taskList = getTasks();  // Replace with your logic to fetch the task list
        taskAdapter.setTaskList(taskList);

        return view;
    }

    private List<Task> getTasks() {
        // Replace with your logic to fetch the task list from a data source
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", "Description 1"));
        taskList.add(new Task("Task 2", "Description 2"));
        taskList.add(new Task("Task 3", "Description 3"));
        return taskList;
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
                descriptionTextView = itemView.findViewById(R.id.title_text_view);
            }
        }
    }
}
