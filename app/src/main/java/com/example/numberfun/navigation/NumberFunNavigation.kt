package com.example.numberfun.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.numberfun.ui.screens.QuizScreen
import com.example.numberfun.viewmodel.StatisticsViewModel
import com.example.numberfun.viewmodel.StatisticsViewModelFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.numberfun.ui.screens.StatisticsScreen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numberfun.data.NumberFunDatabase
import com.example.numberfun.data.QuizRepository
import com.example.numberfun.viewmodel.QuizViewModel
import com.example.numberfun.viewmodel.QuizViewModelFactory
import com.example.numberfun.ui.screens.SettingsScreen
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val HOME = "home"
    const val QUIZ = "quiz"
    const val STATISTICS = "statistics"
    const val SETTINGS = "settings"
}

@Composable
fun NumberFunNavigation() {
    val navController = rememberNavController()
    var difficulty by remember { mutableStateOf("Easy") }
    var soundEnabled by remember { mutableStateOf(true) }

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(Routes.QUIZ) {

            val context = LocalContext.current

            val database = NumberFunDatabase.getDatabase(context)

            val repository = QuizRepository(
                database.quizResultDao()
            )

            val quizViewModel: QuizViewModel = viewModel(
                factory = QuizViewModelFactory(repository)
            )

            QuizScreen(
                viewModel = quizViewModel
            )


        }

        composable(Routes.STATISTICS) {

            val context = LocalContext.current

            val database = NumberFunDatabase.getDatabase(context)

            val repository = QuizRepository(
                database.quizResultDao()
            )

            val statisticsViewModel: StatisticsViewModel = viewModel(
                factory = StatisticsViewModelFactory(repository)
            )

            StatisticsScreen(
                viewModel = statisticsViewModel
            )


        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                difficulty = difficulty,
                soundEnabled = soundEnabled,
                onDifficultyChange = { difficulty = it },
                onSoundChange = { soundEnabled = it }
            )
        }

        }
    }


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("NumberFun")

        Button(onClick = { navController.navigate(Routes.QUIZ) }) {
            Text("Start Quiz")
        }

        Button(onClick = { navController.navigate(Routes.STATISTICS) }) {
            Text("Statistics")
        }

        Button(onClick = { navController.navigate(Routes.SETTINGS) }) {
            Text("Settings")
        }
    }
}

@Composable
fun SimpleScreen(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title)
    }
}