package com.example.numberfun.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {

    var difficulty by remember { mutableStateOf("Easy") }
    var soundEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Difficulty",
                    style = MaterialTheme.typography.titleLarge
                )

                DifficultyOption(
                    title = "Easy",
                    selected = difficulty == "Easy",
                    onSelect = { difficulty = "Easy" }
                )

                DifficultyOption(
                    title = "Medium",
                    selected = difficulty == "Medium",
                    onSelect = { difficulty = "Medium" }
                )

                DifficultyOption(
                    title = "Hard",
                    selected = difficulty == "Hard",
                    onSelect = { difficulty = "Hard" }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Sound",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = if (soundEnabled) "Sound enabled" else "Sound disabled"
                    )
                }

                Switch(
                    checked = soundEnabled,
                    onCheckedChange = {
                        soundEnabled = it
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Current difficulty: $difficulty",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun DifficultyOption(
    title: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )

        Text(text = title)
    }
}