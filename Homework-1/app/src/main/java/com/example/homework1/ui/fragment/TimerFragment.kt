package com.example.homework1.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework1.R
import com.example.homework1.data.service.TimerService
import com.example.homework1.databinding.FragmentTimerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timerButtonBtn.setOnClickListener { startStopTimer() }
        binding.resetBtn.setOnClickListener { resetTimer() }

        serviceIntent = Intent(context, TimerService::class.java)
        requireContext(). registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
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
        binding.timerButtonBtn.text = "Stop"
        timerStarted = true
    }

    private fun stopTimer() {
        requireContext().stopService(serviceIntent)
        binding.timerButtonBtn.text = getString(R.string.start)
        timerStarted = false
        Log.d("klsdjfks", time.toString())
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

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)

}