package com.example.homework1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.data.model.Task
import com.example.homework1.databinding.TaskElementBinding
import com.example.homework1.ui.viewholder.TaskViewHolder

class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    var tasks = emptyList<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var taskCallback: ((Task) -> AlertDialog?)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = TaskElementBinding.inflate(inflate, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], taskCallback)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}