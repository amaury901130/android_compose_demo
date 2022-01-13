package com.rootstrap.android.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow


@Composable
fun PrimaryButton(
    modifier: Modifier,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnable
    ) {
        Text(text = text)
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier,
    onClick: () -> Unit,
    isEnableStateFlow: StateFlow<Boolean>,
    text: String
) {
    val isEnableState by isEnableStateFlow.collectAsState()
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnableState
    ) {
        Text(text = text)
    }
}
