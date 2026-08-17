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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun QuizScreen() {

    var questionNumber by remember { mutableIntStateOf(1) }
    var score by remember { mutableIntStateOf(0) }
    var question by remember { mutableStateOf(generateQuestion()) }
    var feedback by remember { mutableStateOf("") }
    var answerSelected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Question $questionNumber of 10",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Score: $score",
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
                    text = "${question.firstNumber} + ${question.secondNumber} = ?",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        question.answers.forEach { answer ->

            Button(
                onClick = {
                    if (!answerSelected) {
                        answerSelected = true

                        if (answer == question.correctAnswer) {
                            score++
                            feedback = "Correct!"
                        } else {
                            feedback =
                                "Incorrect. The answer is ${question.correctAnswer}."
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(answer.toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (feedback.isNotEmpty()) {
            Text(
                text = feedback,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (answerSelected && questionNumber < 10) {
            Button(
                onClick = {
                    questionNumber++
                    question = generateQuestion()
                    feedback = ""
                    answerSelected = false
                }
            ) {
                Text("Next Question")
            }
        }

        if (answerSelected && questionNumber == 10) {
            Text(
                text = "Quiz complete! Final score: $score / 10",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}