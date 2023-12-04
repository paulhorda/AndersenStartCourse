package com.example.homework1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.data.model.Project
import com.example.homework1.databinding.ProjectElementBinding
import com.example.homework1.ui.viewholder.ProjectViewHolder

class ProjectAdapter : RecyclerView.Adapter<ProjectViewHolder>() {

    var projects = emptyList<Project>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var projectCallback: ((Project) -> Unit?)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ProjectElementBinding.inflate(inflate, parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position], projectCallback)
    }

    override fun getItemCount(): Int {
        return projects.size
    }
}