package com.example.lab7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TrafficLightView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var currentLightState: LightState = LightState.RED
    private val paint = Paint()

    enum class LightState {
        RED,
        YELLOW,
        GREEN
    }

    fun setLightState(state: LightState) {
        currentLightState = state
        invalidate() // Перерисовка View
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val lightSize = width * 0.3f // Размер сигнала относительно ширины светофора
        val padding = 20f // Отступ между сигналами
        val topMargin = (height - (lightSize * 3 + padding * 2)) / 2 // Центрирование по вертикали

        // Устанавливаем цвета для каждого сигнала
        paint.color = if (currentLightState == LightState.RED) Color.RED else Color.GRAY
        canvas.drawRect(
            width / 2f - lightSize / 2,
            topMargin,
            width / 2f + lightSize / 2,
            topMargin + lightSize,
            paint
        )

        paint.color = if (currentLightState == LightState.YELLOW) Color.YELLOW else Color.GRAY
        canvas.drawRect(
            width / 2f - lightSize / 2,
            topMargin + lightSize + padding,
            width / 2f + lightSize / 2,
            topMargin + lightSize * 2 + padding,
            paint
        )

        paint.color = if (currentLightState == LightState.GREEN) Color.GREEN else Color.GRAY
        canvas.drawRect(
            width / 2f - lightSize / 2,
            topMargin + lightSize * 2 + padding * 2,
            width / 2f + lightSize / 2,
            topMargin + lightSize * 3 + padding * 3,
            paint
        )
    }
}
