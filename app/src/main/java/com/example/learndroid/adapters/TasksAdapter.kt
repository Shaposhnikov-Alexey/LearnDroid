package com.example.learndroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndroid.data.entity.Task
import com.example.learndroid.databinding.TaskRecyclerItemBinding

class TasksAdapter(
    private val listener: (position: Int) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    private var tasks: MutableList<Task> = emptyList<Task>().toMutableList()

    class TasksViewHolder(
        val binding: TaskRecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TasksViewHolder(
            TaskRecyclerItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val current = tasks[position]
        val name = current.taskName
        holder.binding.taskItem.apply {
            text = name
            holder.binding.taskItem.setOnClickListener {
                listener(position)
            }
            if (current.isDone)
                isEnabled = true
        }
    }

    internal fun setTasks(words: List<Task>) {
        this.tasks = words.toMutableList()
        notifyDataSetChanged()
    }

    internal fun getTaskInfo(position: Int): Task {
        return tasks[position]
    }

    internal fun insertTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    override fun getItemCount(): Int = tasks.size
}
