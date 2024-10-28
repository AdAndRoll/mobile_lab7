package com.example.lab7

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class Task6Activity : AppCompatActivity(), NewPageFragment.PageCloseListener {

    private lateinit var pageCountText: TextView
    private lateinit var addPageButton: Button
    private lateinit var removePageButton: Button

    private var addedPages = 0
    private var removedPages = 0
    private val fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task6)

        pageCountText = findViewById(R.id.page_count_text)
        addPageButton = findViewById(R.id.add_page_button)
        removePageButton = findViewById(R.id.remove_page_button)

        addPageButton.setOnClickListener {
            addPage()
        }

        removePageButton.setOnClickListener {
            closeLastPage()
        }

        updatePageCountText()
    }

    private fun addPage() {
        // Увеличиваем счетчик добавленных страниц
        addedPages++

        // Создаем и добавляем новый фрагмент в контейнер
        val newPageFragment = NewPageFragment()
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newPageFragment)
        transaction.addToBackStack(null) // Добавляем в стек назад
        transaction.commit()

        updatePageCountText()
    }

    private fun closeLastPage() {
        if (fragmentManager.backStackEntryCount > 0) { // Проверяем, есть ли страницы в стеке
            fragmentManager.popBackStack() // Удаляем верхний фрагмент из стека
            removedPages++ // Увеличиваем счетчик закрытых страниц
            updatePageCountText()
        }
    }

    // Обработчик закрытия страницы
    override fun onPageClosed() {
        removedPages++ // Увеличиваем счетчик закрытых страниц
        updatePageCountText()
    }

    private fun updatePageCountText() {
        pageCountText.text = "Pages Created: $addedPages, Pages Closed: $removedPages"
    }
}
