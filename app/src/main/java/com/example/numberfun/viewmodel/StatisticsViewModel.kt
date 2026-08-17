package com.example.numberfun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numberfun.data.QuizRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class StatisticsUiState(
    val quizzesCompleted: Int = 0,
    val questionsAnswered: Int = 0,
    val correctAnswers: Int = 0,
    val bestScore: Int = 0
) {
    val accuracy: Int
        get() = if (questionsAnswered > 0) {
            (correctAnswers * 100) / questionsAnswered
        } else {
            0
        }
}

class StatisticsViewModel(
    repository: QuizRepository
) : ViewModel() {

    val uiState: StateFlow<StatisticsUiState> =
        combine(
            repository.getQuizCount(),
            repository.getTotalQuestions(),
            repository.getTotalCorrect(),
            repository.getBestScore()
        ) { quizCount, totalQuestions, totalCorrect, bestScore ->

            StatisticsUiState(
                quizzesCompleted = quizCount,
                questionsAnswered = totalQuestions,
                correctAnswers = totalCorrect,
                bestScore = bestScore
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StatisticsUiState()
        )
}