package com.example.numberfun.viewmodel

import androidx.lifecycle.ViewModel
import com.example.numberfun.ui.screens.MathsQuestion
import com.example.numberfun.ui.screens.generateQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizUiState(
    val questionNumber: Int = 1,
    val score: Int = 0,
    val question: MathsQuestion = generateQuestion(),
    val feedback: String = "",
    val answerSelected: Boolean = false,
    val quizComplete: Boolean = false
)

class QuizViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun submitAnswer(answer: Int) {
        if (_uiState.value.answerSelected) return

        val current = _uiState.value
        val isCorrect = answer == current.question.correctAnswer

        _uiState.value = current.copy(
            score = if (isCorrect) current.score + 1 else current.score,
            feedback = if (isCorrect) {
                "Correct!"
            } else {
                "Incorrect. The answer is ${current.question.correctAnswer}."
            },
            answerSelected = true
        )
    }

    fun nextQuestion() {
        val current = _uiState.value

        if (current.questionNumber >= 10) {
            _uiState.value = current.copy(
                quizComplete = true
            )
            return
        }

        _uiState.value = current.copy(
            questionNumber = current.questionNumber + 1,
            question = generateQuestion(),
            feedback = "",
            answerSelected = false
        )
    }

    fun restartQuiz() {
        _uiState.value = QuizUiState()
    }
}