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

    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    init {
        loadMathsFact()
    }

    fun loadMathsFact() {

        viewModelScope.launch {

            _uiState.value = HomeUiState(
                isLoading = true
            )

            try {

                val choice =
                    (System.currentTimeMillis() % 5).toInt()

                val expression = when (choice) {
                    0 -> "12*12"
                    1 -> "144/12"
                    2 -> "25*4"
                    3 -> "9*9"
                    else -> "15+27"
                }

                val answer =
                    RetrofitClient.wikipediaApi
                        .calculate(expression)

                _uiState.value = HomeUiState(
                    isLoading = false,
                    mathsFact =
                        "Can you solve $expression? The answer is $answer."
                )

            } catch (e: Exception) {

                _uiState.value = HomeUiState(
                    isLoading = false,
                    errorMessage =
                        "Unable to load maths fact."
                )
            }
        }
    }
}