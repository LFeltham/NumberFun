package com.example.numberfun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.numberfun.data.QuizRepository

class QuizViewModelFactory(
    private val repository: QuizRepository,
    private val difficulty: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(repository, difficulty) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}