package com.example.lab7

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Task5Activity : AppCompatActivity() {

    private lateinit var hourView: HourView
    private lateinit var minuteView: MinuteView
    private lateinit var secondView: SecondView
    private lateinit var startStopButton: Button

    private var isRunning = false
    private var elapsedSeconds = 0

    private val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            elapsedSeconds++
            updateTimerDisplay()
        }

        override fun onFinish() {
            // Не используется, так как длительность бесконечная
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task5)

        hourView = findViewById(R.id.hourView)
        minuteView = findViewById(R.id.minuteView)
        secondView = findViewById(R.id.secondView)
        startStopButton = findViewById(R.id.startStopButton)

        startStopButton.setOnClickListener {
            toggleTimer()
        }
    }

    private fun toggleTimer() {
        isRunning = !isRunning
        if (isRunning) {
            startStopButton.text = "Stop"
            timer.start()
        } else {
            startStopButton.text = "Start"
            timer.cancel()
        }
    }

    private fun updateTimerDisplay() {
        val hours = elapsedSeconds / 3600
        val minutes = (elapsedSeconds % 3600) / 60
        val seconds = elapsedSeconds % 60

        hourView.setHour(hours)
        minuteView.setMinute(minutes)
        secondView.setSecond(seconds)
    }
}
