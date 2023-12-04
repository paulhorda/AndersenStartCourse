package com.example.homework1.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.data.model.Project
import com.example.homework1.databinding.ProjectElementBinding

class ProjectViewHolder(val binding: ProjectElementBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(project: Project, projectCallback: ((Project) -> Unit?)?) {
        binding.projectNameTv.text = project.name.trim()

        binding.root.setOnClickListener { projectCallback?.invoke(project) }
    }
}