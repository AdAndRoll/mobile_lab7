package com.example.lab7

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Task1Activity : AppCompatActivity() {

    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var pedestrianIcon: ImageView
    private val handler = Handler(Looper.getMainLooper())
    private var currentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)

        redLight = findViewById(R.id.red_light)
        yellowLight = findViewById(R.id.yellow_light)
        greenLight = findViewById(R.id.green_light)
        pedestrianIcon = findViewById(R.id.pedestrian_icon)

        startTrafficLight()
    }

    private fun startTrafficLight() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                updateLights()
                handler.postDelayed(this, 2000)
            }
        }, 0)
    }

    private fun updateLights() {
        when (currentState) {
            0 -> {
                // Красный свет
                redLight.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                yellowLight.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                greenLight.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                pedestrianIcon.visibility = View.GONE
                currentState = 1
            }
            1 -> {
                // Желтый свет
                redLight.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                yellowLight.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
                greenLight.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                pedestrianIcon.visibility = View.GONE
                currentState = 2
            }
            2 -> {
                // Зеленый свет и анимация человечка
                redLight.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                yellowLight.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                greenLight.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                pedestrianIcon.visibility = View.VISIBLE
                animatePedestrian()
                currentState = 0
            }
        }
    }

    private fun animatePedestrian() {
        val screenWidth = resources.displayMetrics.widthPixels
        pedestrianIcon.visibility = View.VISIBLE
        val animator = ObjectAnimator.ofFloat(pedestrianIcon, "translationX", -300f, screenWidth.toFloat())
        animator.duration = 3000 // Длительность анимации
        animator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
