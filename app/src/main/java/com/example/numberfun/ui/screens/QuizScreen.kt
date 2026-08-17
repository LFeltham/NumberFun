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
import androidx.compose.runtime.collectAsState
import com.example.numberfun.viewmodel.QuizViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class MathsQuestion(
    val firstNumber: Int,
    val secondNumber: Int,
    val correctAnswer: Int,
    val answers: List<Int>
)

fun generateQuestion(): MathsQuestion {
    val first = Random.nextInt(1, 11)
    val second = Random.nextInt(1, 11)
    val correct = first + second

    val wrongAnswers = mutableSetOf<Int>()

    while (wrongAnswers.size < 3) {
        val wrong = correct + Random.nextInt(-5, 6)

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
                    text = "${uiState.question.firstNumber} + ${uiState.question.secondNumber} = ?",
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
                text = "Quiz complete! Final score: ${uiState.score} / 10",
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