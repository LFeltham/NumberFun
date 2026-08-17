package com.example.numberfun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numberfun.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val mathsFact: String = "",
    val errorMessage: String = ""
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMathsFact()
    }

    fun loadMathsFact() {
        viewModelScope.launch {

            _uiState.value = HomeUiState(
                isLoading = true
            )

            try {

                val expressions = listOf(
                    "12*12",
                    "144/12",
                    "25*4",
                    "9*9",
                    "15+27"
                )

                val expression = expressions.random()

                val answer =
                    RetrofitClient.wikipediaApi
                        .calculate(expression)

                _uiState.value = HomeUiState(
                    mathsFact =
                        "Can you solve $expression? The answer is $answer."
                )

            } catch (e: Exception) {

                _uiState.value = HomeUiState(
                    errorMessage =
                        "Unable to load maths fact."
                )
            }
        }
    }
}