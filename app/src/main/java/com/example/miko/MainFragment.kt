package com.example.miko

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.miko.databinding.FragmentMainBinding
import java.util.*



class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var timerTask: TimerTask
    private var time: Double= 0.0

    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timer = Timer()
        startTimer()
        Log.d("Activity Status", "OnCreate Called")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title="Main Fragment"

        Log.d("Activity Status", "OnCreate View Called")
        binding.timerText.setText(getTimerText())


        binding.btnCamera.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_mainFragment_to_cameraFragment2)
        }

        binding.btnMic.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_mainFragment_to_micFragment)
        }
        return binding.root
    }



    private fun startTimer() {
        timerTask = object : TimerTask() {
            override fun run() {
                (activity as AppCompatActivity).runOnUiThread(Runnable {
                    time++
                    binding.timerText.setText(getTimerText())
                })
            }
        }
            timer.scheduleAtFixedRate(timerTask, 0, 1000)


    }


    private fun getTimerText(): String? {
        val rounded = Math.round(time).toInt()
        val seconds = rounded % 86400 % 3600 % 60
        val minutes = rounded % 86400 % 3600 / 60
        val hours = rounded % 86400 / 3600
        return formatTime(seconds, minutes, hours)
    }

    private fun formatTime(seconds: Int, minutes: Int, hours: Int): String? {
        return String.format("%02d", hours) + " : " + String.format(
            "%02d",
            minutes
        ) + " : " + String.format("%02d", seconds)
    }


}