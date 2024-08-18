package com.example.to_do_list

// TaskAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.textViewTaskName)
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.checkBoxCompleted)
        val deleteButton: ImageButton = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskCheckBox.isChecked = task.isCompleted

        holder.taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
        }

        holder.deleteButton.setOnClickListener {
            tasks.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, tasks.size)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}
