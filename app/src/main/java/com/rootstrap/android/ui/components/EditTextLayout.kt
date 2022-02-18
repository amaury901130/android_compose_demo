package com.rootstrap.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rootstrap.android.theme.Spacing
import com.rootstrap.android.util.InputWrapper
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EditTextLayout(
    inputWrapperStateFlow: StateFlow<InputWrapper>,
    onValueChange: (value: String) -> Unit,
    onImeKeyAction: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String
) {
    val inputWrapperState by inputWrapperStateFlow.collectAsState()
    Column {
        TextField(
            modifier = Modifier.padding(vertical = Spacing.l),
            label = { Text(label) },
            value = inputWrapperState.content,
            onValueChange = { onValueChange(it) },
            isError = inputWrapperState.errorId != null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            keyboardActions = KeyboardActions(
                onAny = { onImeKeyAction?.invoke() },
            ),
        )
        if (inputWrapperState.errorId != null) {
            Text("Error code: ${inputWrapperState.errorId}")
        }
    }
}
