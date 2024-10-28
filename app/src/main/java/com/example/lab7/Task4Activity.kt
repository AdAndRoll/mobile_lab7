package com.example.lab7

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Task4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task4)

        // Находим кнопки по id и задаем обработчики нажатий
        findViewById<ButtonComponent>(R.id.defaultButton).setOnClickListener {
            showToast("Default Button clicked")
        }

        findViewById<ButtonComponent>(R.id.redButton).setOnClickListener {
            showToast("Red Button clicked")
        }

        findViewById<ButtonComponent>(R.id.greenButton).setOnClickListener {
            showToast("Green Button clicked")
        }

        findViewById<ButtonComponent>(R.id.blueButton).setOnClickListener {
            showToast("Blue Button clicked")
        }

        findViewById<ButtonComponent>(R.id.yellowButton).setOnClickListener {
            showToast("Yellow Button clicked")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
