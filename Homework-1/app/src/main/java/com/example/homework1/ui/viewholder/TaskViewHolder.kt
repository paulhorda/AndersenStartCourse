package com.example.homework1.ui.viewholder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.data.model.Task
import com.example.homework1.databinding.TaskElementBinding
import com.example.homework1.util.getTimeStringFromDouble

class TaskViewHolder(val binding: TaskElementBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, taskCallback: ((Task) -> AlertDialog?)?) {
        binding.taskNameTv.text = task.name
//        binding.statusTv.text = task.id.toString()
        binding.timeSpentTv.text = getTimeStringFromDouble(task.time)

        binding.root.setOnClickListener { taskCallback?.invoke(task) }
    }
}