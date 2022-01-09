package com.rootstrap.android.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    isEnable: Boolean = true,
    text: String
) {
    Button(onClick = onClick, enabled = isEnable) {
        Text(text = text)
    }
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    isEnableStateFlow: StateFlow<Boolean>,
    text: String
) {
    val isEnableState by isEnableStateFlow.collectAsState()
    Button(onClick = onClick, enabled = isEnableState) {
        Text(text = text)
    }
}
