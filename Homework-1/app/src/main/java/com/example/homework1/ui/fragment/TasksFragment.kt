package com.example.homework1.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework1.R
import com.example.homework1.databinding.FragmentTasksBinding
import com.example.homework1.databinding.FragmentTimerBinding
import com.example.homework1.ui.controller.TaskController
import com.example.homework1.ui.dialogs.NewProjectDialog
import com.example.homework1.ui.dialogs.NewTaskDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TasksFragment : Fragment() {

    private lateinit var binding: FragmentTasksBinding
    @Inject
    lateinit var taskController:TaskController

    val args: TasksFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tasksRv.adapter = taskController.adapter

        taskController.getTasksLiveData(args.projectId).observe(viewLifecycleOwner){ tasks ->
            taskController.adapter.tasks = tasks
        }
         val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
         val editor: SharedPreferences.Editor = sharedPreferences.edit()

        taskController.adapter.taskCallback ={
            editor.putLong("taskId", it.id)
            editor.putString("taskName", it.name)
            editor.apply()

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.success))
                .setMessage(getString(R.string.success_add_task_for_timer))
                .setPositiveButton("OK") { _, _ ->
                    findNavController().popBackStack()
                }
                .show()
        }

        binding.addNewTaskFab.setOnClickListener {
            showAddNewTaskDialog()
        }
    }

    private fun showAddNewTaskDialog(){
        val dialog = NewTaskDialog(requireActivity()) { taskName ->
            lifecycleScope.launch {
                taskController.saveTask(args.projectId, taskName)
            }
        }
        dialog.show()
    }

}