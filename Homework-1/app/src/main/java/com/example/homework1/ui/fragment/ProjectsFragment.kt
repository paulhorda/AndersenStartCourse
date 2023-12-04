package com.example.homework1.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework1.databinding.FragmentProjectsBinding
import com.example.homework1.ui.controller.ProjectController
import com.example.homework1.ui.dialogs.NewProjectDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProjectsFragment : Fragment() {

    private lateinit var binding: FragmentProjectsBinding

    @Inject
    lateinit var projectController: ProjectController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        binding.projectsRv.adapter = projectController.adapter

        projectController.projects.observe(viewLifecycleOwner) { projects ->
            projectController.adapter.projects = projects
        }


        projectController.adapter.projectCallback = { project ->
            val action =
                ProjectsFragmentDirections.actionProjectsFragmentToTasksFragment(project.id)
            editor.putString("projectName", project.name)
            editor.apply()
            findNavController().navigate(action)
        }



        binding.addNewProjectFab.setOnClickListener {
            showAddNewProjectDialog()
        }
    }

    private fun showAddNewProjectDialog() {
        val dialog = NewProjectDialog(requireActivity()) { projectName ->
            lifecycleScope.launch {
                projectController.saveProject(projectName)
            }
        }
        dialog.show()
    }
}