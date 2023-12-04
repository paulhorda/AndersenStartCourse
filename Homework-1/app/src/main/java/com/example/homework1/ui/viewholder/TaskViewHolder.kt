package com.example.homework1.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.data.model.Task
import com.example.homework1.databinding.TaskElementBinding

class TaskViewHolder(val binding: TaskElementBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, taskCallback: ((Task) -> Unit?)?) {
        binding.taskNameTv.text = task.name
        binding.statusTv.text = task.id.toString()
//        binding.timeSpentTv.text = "${task.time}m"

        binding.root.setOnClickListener { taskCallback?.invoke(task) }
    }
}