package com.example.numberfun.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(Routes.QUIZ) {
            SimpleScreen("Quiz Screen")
        }

        composable(Routes.STATISTICS) {
            SimpleScreen("Statistics Screen")
        }

        composable(Routes.SETTINGS) {
            SimpleScreen("Settings Screen")
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