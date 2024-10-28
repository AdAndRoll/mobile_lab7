package com.example.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewPageFragment : Fragment() {

    private var callback: PageCloseListener? = null

    // Интерфейс для обратной связи с активностью
    interface PageCloseListener {
        fun onPageClosed()
    }

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        callback = context as? PageCloseListener // Привязываем активность как слушателя
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Инфлейтим макет фрагмента
        val view = inflater.inflate(R.layout.fragment_new_page, container, false)

        val textView = view.findViewById<TextView>(R.id.fragment_text)
        textView.text = "This is a new page!"

        val closeButton = view.findViewById<Button>(R.id.close_button)
        closeButton.setOnClickListener {
            // Закрываем фрагмент и уведомляем активность
            callback?.onPageClosed()
            requireActivity().onBackPressed() // Закрываем фрагмент
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callback = null // Освобождаем ссылку на активность
    }
}
