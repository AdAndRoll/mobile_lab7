package com.example.lab7

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Task2Activity : AppCompatActivity() {

    private lateinit var animatedText: TextView
    private val initialColor = Color.BLACK
    private val targetColor = Color.RED
    private val rotationAngle = 180f
    private val moveDistance = 1000f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        animatedText = findViewById(R.id.animated_text)

        // Устанавливаем слушатель для нажатия и отпускания
        animatedText.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> startAnimation()
                MotionEvent.ACTION_UP -> reverseAnimation()
            }
            true
        }
    }

    private fun startAnimation() {
        // Анимация перемещения вниз
        val moveDown = ObjectAnimator.ofFloat(animatedText, "translationY", moveDistance)
        moveDown.duration = 1000

        // Анимация поворота
        val rotate = ObjectAnimator.ofFloat(animatedText, "rotation", rotationAngle)
        rotate.duration = 1000

        // Анимация изменения цвета текста
        animatedText.setTextColor(targetColor)

        // Запуск анимации
        moveDown.start()
        rotate.start()
    }

    private fun reverseAnimation() {
        // Анимация перемещения вверх
        val moveUp = ObjectAnimator.ofFloat(animatedText, "translationY", 0f)
        moveUp.duration = 1000

        // Анимация поворота обратно
        val rotateBack = ObjectAnimator.ofFloat(animatedText, "rotation", 0f)
        rotateBack.duration = 1000

        // Восстановление начального цвета текста
        animatedText.setTextColor(initialColor)

        // Запуск анимации
        moveUp.start()
        rotateBack.start()
    }
}
