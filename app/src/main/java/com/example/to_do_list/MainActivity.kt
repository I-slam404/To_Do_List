package com.example.to_do_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView with the TaskAdapter
        taskAdapter = TaskAdapter(tasks)
        binding.recyclerView.adapter = taskAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Handle Add Task button click
        binding.buttonAdd.setOnClickListener {
            val taskName = binding.editTextTask.text.toString()
            if (taskName.isNotEmpty()) {
                tasks.add(Task(taskName))
                taskAdapter.notifyItemInserted(tasks.size - 1)
                binding.editTextTask.text.clear()
            }
        }
    }
}