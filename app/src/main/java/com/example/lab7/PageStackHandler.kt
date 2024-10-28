package com.example.lab7

class PageStackHandler {
    var addedPages = 0
        private set
    var removedPages = 0
        private set

    fun addPage() {
        addedPages++
    }

    fun removePage() {
        if (addedPages > 0) {
            removedPages++
            addedPages-- // assuming you cannot have negative pages
        }
    }
}
