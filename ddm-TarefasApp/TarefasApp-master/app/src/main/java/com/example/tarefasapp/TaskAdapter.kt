package com.example.tarefasapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tarefasapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        val binding = holder.binding

        binding.textTaskName.text = task.name
        binding.textTaskDescription.text = task.description
        binding.textTaskDescription.visibility =
            if (task.description.isBlank()) android.view.View.GONE else android.view.View.VISIBLE

        if (task.isCompleted) {
            binding.textTaskName.paintFlags =
                binding.textTaskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.buttonToggleCompleted.text =
                holder.itemView.context.getString(R.string.mark_pending)
        } else {
            binding.textTaskName.paintFlags =
                binding.textTaskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            binding.buttonToggleCompleted.text =
                holder.itemView.context.getString(R.string.mark_completed)
        }

        binding.buttonToggleCompleted.setOnClickListener {
            task.isCompleted = !task.isCompleted
            notifyItemChanged(holder.bindingAdapterPosition)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
}
