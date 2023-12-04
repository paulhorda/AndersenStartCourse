package com.example.homework1.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homework1.R
import com.example.homework1.data.service.TimerService
import com.example.homework1.databinding.FragmentTimerBinding
import com.example.homework1.ui.controller.TaskController
import com.example.homework1.util.getTimeStringFromDouble
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding

    @Inject
    lateinit var taskController: TaskController

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private var lastPause = 0.0

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()


        val taskName = sharedPreferences.getString("taskName", "No select task")
        binding.taskNameTv.text = taskName
        binding.projectNameTv.text = sharedPreferences.getString("projectName", "No select project")

        binding.timerButtonBtn.setOnClickListener { startStopTimer() }
        binding.resetBtn.setOnClickListener { resetTimer() }

        serviceIntent = Intent(context, TimerService::class.java)
        requireContext().registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        lastPause = 0.0
        binding.timerTv.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if (timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        requireContext().startService(serviceIntent)
        binding.timerButtonBtn.text = getString(R.string.stop)
        timerStarted = true
    }

    private fun stopTimer() {
        requireContext().stopService(serviceIntent)
        binding.timerButtonBtn.text = getString(R.string.start)
        timerStarted = false

        lifecycleScope.launch {
            if (sharedPreferences.getLong("taskId", 0)!=0L)
            taskController.updateTask(sharedPreferences.getLong("taskId",0), time - lastPause)
        }

        lastPause = time
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("TimerFragment", "Received broadcast.")
            intent?.let {
                time = it.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
                Log.d("TimerFragment", "Updating UI with time: $time")
                binding.timerTv.text = getTimeStringFromDouble(time)
            }
        }
    }


}