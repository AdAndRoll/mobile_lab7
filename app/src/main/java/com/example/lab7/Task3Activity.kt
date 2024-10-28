package com.example.lab7

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Task3Activity : AppCompatActivity() {

    private lateinit var trafficLight: TrafficLightView
    private lateinit var pedestrianIcon: ImageView
    private val handler = Handler(Looper.getMainLooper())
    private var currentStateIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)

        // Инициализация светофора и человечка
        trafficLight = findViewById(R.id.trafficLightView)
        pedestrianIcon = findViewById(R.id.pedestrian_icon)

        // Установка начального состояния на красный
        trafficLight.setLightState(TrafficLightView.LightState.RED)
        pedestrianIcon.visibility = View.GONE

        // Запуск смены сигналов светофора
        startTrafficLightCycle()
    }

    private fun startTrafficLightCycle() {
        val lightStates = TrafficLightView.LightState.values()
        currentStateIndex = 0

        // Устанавливаем начальное состояние на красный
        trafficLight.setLightState(lightStates[currentStateIndex])

        Thread {
            while (true) {
                runOnUiThread {
                    // Обновляем состояние светофора
                    trafficLight.setLightState(lightStates[currentStateIndex])

                    // Анимация для человечка при зеленом свете
                    if (lightStates[currentStateIndex] == TrafficLightView.LightState.GREEN) {
                        pedestrianIcon.visibility = View.VISIBLE
                        animatePedestrian()
                    } else {
                        pedestrianIcon.visibility = View.GONE
                    }

                    // Переключаем состояние
                    currentStateIndex = (currentStateIndex + 1) % lightStates.size
                }
                Thread.sleep(3000) // Задержка 3 секунды
            }
        }.start()
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
