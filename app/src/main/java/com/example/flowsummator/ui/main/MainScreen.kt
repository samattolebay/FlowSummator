package com.example.flowsummator.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.flowsummator.R

@Composable
fun MainScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val resultText = stringResource(id = R.string.result, uiState.value.sums.joinToString())

    val number = remember { mutableStateOf("") }
    MainScreenContent(
        modifier = modifier,
        numberText = number.value,
        onNumberTextChange = { number.value = it },
        errorText = uiState.value.error,
        onEnterNumberClicked = { viewModel.onEnterNumberClicked(number.value) },
        resultText = resultText
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    modifier: Modifier,
    numberText: String,
    onNumberTextChange: (String) -> Unit,
    errorText: String?,
    onEnterNumberClicked: () -> Unit,
    resultText: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = numberText,
            onValueChange = onNumberTextChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = errorText != null,
            supportingText = {
                if (errorText != null)
                    Text(text = errorText)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onEnterNumberClicked,
            enabled = numberText.isNotEmpty()
        ) {
            Text(text = stringResource(id = R.string.launch))
        }
        Text(text = resultText, modifier = Modifier.fillMaxWidth())
    }
}
