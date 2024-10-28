package com.example.lab7

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class ButtonComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ButtonComponent)
        val buttonText = attributes.getString(R.styleable.ButtonComponent_buttonText) ?: "Default Text"
        val buttonColor = attributes.getColor(R.styleable.ButtonComponent_buttonColor, Color.LTGRAY)

        text = buttonText
        setBackgroundColor(buttonColor)

        attributes.recycle()
    }
}
