package com.example.numberfun.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatisticsScreen() {

    // Temporary values.
    // These will later be loaded from the Room database.
    val quizzesCompleted = 0
    val questionsAnswered = 0
    val correctAnswers = 0
    val bestScore = 0

    val accuracy =
        if (questionsAnswered > 0) {
            (correctAnswers * 100) / questionsAnswered
        } else {
            0
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Your Progress",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Keep practising to improve your maths skills!",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        StatisticCard(
            title = "Quizzes Completed",
            value = quizzesCompleted.toString()
        )

        StatisticCard(
            title = "Questions Answered",
            value = questionsAnswered.toString()
        )

        StatisticCard(
            title = "Correct Answers",
            value = correctAnswers.toString()
        )

        StatisticCard(
            title = "Accuracy",
            value = "$accuracy%"
        )

        StatisticCard(
            title = "Best Score",
            value = "$bestScore / 10"
        )
    }
}

@Composable
fun StatisticCard(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}