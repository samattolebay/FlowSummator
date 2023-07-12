package com.example.flowsummator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    private var job: Job? = null

    fun onEnterNumberClicked(numberText: String) {
        val number = try {
            numberText.trim().toInt()
        } catch (e: NumberFormatException) {
            _uiState.update {
                it.copy(error = "Must enter only decimal!")
            }
            return
        }

        job?.cancel()
        job = viewModelScope.launch {
            withContext(Dispatchers.Default) {
                var sum = 0
                val newSums = mutableListOf<Int>()
                for (index in 1..number) {
                    delay(index * 100L)

                    sum += index
                    newSums.add(sum)
                    _uiState.value = MainUiState(sums = newSums.toTypedArray())
                }
            }
        }
    }
}
