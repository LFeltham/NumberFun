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
                val response =
                    RetrofitClient.wikipediaApi.getSummary("Mathematics")

                _uiState.value = HomeUiState(
                    mathsFact = response.extract
                )

            } catch (e: Exception) {

                _uiState.value = HomeUiState(
                    errorMessage = "Unable to load maths fact."
                )
            }
        }
    }
}