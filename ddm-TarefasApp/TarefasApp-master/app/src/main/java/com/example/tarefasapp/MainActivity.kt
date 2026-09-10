package com.example.tarefasapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarefasapp.databinding.ActivityMainBinding
import com.example.tarefasapp.databinding.DialogAddTaskBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        adapter = TaskAdapter(tasks)
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = adapter
        updateEmptyState()

        binding.fabAddTask.setOnClickListener { showAddTaskDialog() }
    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

        AlertDialog.Builder(this)
            .setTitle(R.string.add_task)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.add) { _, _ ->
                val name = dialogBinding.editTaskName.text?.toString()?.trim().orEmpty()
                val description = dialogBinding.editTaskDescription.text?.toString()?.trim().orEmpty()

                if (name.isEmpty()) {
                    Toast.makeText(this, R.string.empty_fields_error, Toast.LENGTH_SHORT).show()
                } else {
                    adapter.addTask(Task(name, description))
                    binding.recyclerViewTasks.scrollToPosition(adapter.itemCount - 1)
                    updateEmptyState()
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun updateEmptyState() {
        binding.textEmpty.visibility =
            if (tasks.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
    }
}
