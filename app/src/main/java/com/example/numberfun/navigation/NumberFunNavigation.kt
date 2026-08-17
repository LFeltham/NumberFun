package com.example.numberfun.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import com.example.numberfun.ui.screens.QuizScreen
import com.example.numberfun.viewmodel.StatisticsViewModel
import com.example.numberfun.viewmodel.StatisticsViewModelFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numberfun.viewmodel.HomeViewModel
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
        @Composable
        fun HomeScreen(
            navController: NavHostController,
            homeViewModel: HomeViewModel = viewModel()
        ) {
            val uiState by homeViewModel.uiState.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "NumberFun",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Practise maths, track your progress and improve your skills.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Maths Fact",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        when {
                            uiState.isLoading -> {
                                Text("Loading maths fact...")
                            }

                            uiState.errorMessage.isNotEmpty() -> {
                                Text(uiState.errorMessage)

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = {
                                        homeViewModel.loadMathsFact()
                                    }
                                ) {
                                    Text("Try Again")
                                }
                            }

                            uiState.mathsFact.isNotEmpty() -> {
                                Text(
                                    text = uiState.mathsFact,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.QUIZ)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start Quiz")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.STATISTICS)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Statistics")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.SETTINGS)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Settings")
                }
            }

        }

        composable(Routes.QUIZ) {

            val context = LocalContext.current

            val database = NumberFunDatabase.getDatabase(context)

            val repository = QuizRepository(
                database.quizResultDao()
            )

            val quizViewModel: QuizViewModel = viewModel(
                factory = QuizViewModelFactory(
                    repository = repository,
                    difficulty = difficulty
                )
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