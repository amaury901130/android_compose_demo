package com.rootstrap.android.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.rootstrap.android.R
import com.rootstrap.android.theme.Spacing
import com.rootstrap.android.ui.components.EditTextLayout
import com.rootstrap.android.ui.components.PrimaryButton
import com.rootstrap.android.ui.viewmodel.SignUpViewModel

@Composable
fun SingUpForm(viewModel: SignUpViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(Spacing.l)) {
        EditTextLayout(
            inputWrapperStateFlow = viewModel.email,
            onValueChange = viewModel::onNameEntered,
            keyboardType = KeyboardType.Email
        )
        EditTextLayout(
            inputWrapperStateFlow = viewModel.password,
            onValueChange = viewModel::onPasswordEntered,
            keyboardType = KeyboardType.NumberPassword
        )
        PrimaryButton(
            onClick = viewModel::onSignUp,
            isEnableStateFlow = viewModel.areInputsValid,
            text = context.getString(R.string.sign_up)
        )
    }
}
