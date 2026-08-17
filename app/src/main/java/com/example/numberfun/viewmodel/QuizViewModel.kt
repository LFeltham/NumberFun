package com.example.numberfun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numberfun.data.QuizRepository
import com.example.numberfun.ui.screens.MathsQuestion
import com.example.numberfun.ui.screens.generateQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizUiState(
    val questionNumber: Int = 1,
    val score: Int = 0,
    val question: MathsQuestion,
    val feedback: String = "",
    val answerSelected: Boolean = false,
    val quizComplete: Boolean = false,
    val resultSaved: Boolean = false
)

class QuizViewModel(
    private val repository: QuizRepository,
    private val difficulty: String
) : ViewModel() {

    private fun createQuestion(): MathsQuestion {
        return generateQuestion(difficulty)
    }

    private val _uiState = MutableStateFlow(
        QuizUiState(
            question = createQuestion()
        )
    )

    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun submitAnswer(answer: Int) {
        if (_uiState.value.answerSelected) {
            return
        }

        val current = _uiState.value
        val isCorrect = answer == current.question.correctAnswer

        _uiState.value = current.copy(
            score = if (isCorrect) {
                current.score + 1
            } else {
                current.score
            },
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

            saveResult()
            return
        }

        _uiState.value = current.copy(
            questionNumber = current.questionNumber + 1,
            question = createQuestion(),
            feedback = "",
            answerSelected = false
        )
    }

    private fun saveResult() {
        val current = _uiState.value

        if (current.resultSaved) {
            return
        }

        viewModelScope.launch {
            repository.saveQuizResult(
                score = current.score,
                totalQuestions = 10,
                difficulty = difficulty
            )

            _uiState.value = _uiState.value.copy(
                resultSaved = true
            )
        }
    }

    fun restartQuiz() {
        _uiState.value = QuizUiState(
            question = createQuestion()
        )
    }
}