package com.example.numberfun.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.numberfun.viewmodel.QuizViewModel

data class MathsQuestion(
    val firstNumber: Int,
    val secondNumber: Int,
    val correctAnswer: Int,
    val answers: List<Int>
)

fun generateQuestion(
    difficulty: String = "Easy"
): MathsQuestion {

    val range = when (difficulty) {
        "Medium" -> 1..20
        "Hard" -> 1..50
        else -> 1..10
    }

    val first = range.random()
    val second = range.random()
    val correct = first + second

    val wrongAnswers = mutableSetOf<Int>()

    while (wrongAnswers.size < 3) {

        val variation = when (difficulty) {
            "Medium" -> (-10..10).random()
            "Hard" -> (-20..20).random()
            else -> (-5..5).random()
        }

        val wrong = correct + variation

        if (wrong >= 0 && wrong != correct) {
            wrongAnswers.add(wrong)
        }
    }

    val answers = (wrongAnswers + correct).shuffled()

    return MathsQuestion(
        firstNumber = first,
        secondNumber = second,
        correctAnswer = correct,
        answers = answers
    )
}

@Composable
fun QuizScreen(
    viewModel: QuizViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Question ${uiState.questionNumber} of 10",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Score: ${uiState.score}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${uiState.question.firstNumber} + " +
                            "${uiState.question.secondNumber} = ?",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        uiState.question.answers.forEach { answer ->

            Button(
                onClick = {
                    viewModel.submitAnswer(answer)
                },
                enabled = !uiState.answerSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(answer.toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.feedback.isNotEmpty()) {
            Text(
                text = uiState.feedback,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.answerSelected && !uiState.quizComplete) {

            Button(
                onClick = {
                    viewModel.nextQuestion()
                }
            ) {
                Text(
                    if (uiState.questionNumber == 10) {
                        "Finish Quiz"
                    } else {
                        "Next Question"
                    }
                )
            }
        }

        if (uiState.quizComplete) {

            Text(
                text = "Quiz complete! Final score: " +
                        "${uiState.score} / 10",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.restartQuiz()
                }
            ) {
                Text("Play Again")
            }
        }
    }
}