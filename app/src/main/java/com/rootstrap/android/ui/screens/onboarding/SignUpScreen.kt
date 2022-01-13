package com.rootstrap.android.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rootstrap.android.R
import com.rootstrap.android.theme.Spacing
import com.rootstrap.android.ui.MainDestinations
import com.rootstrap.android.ui.components.EditTextLayout
import com.rootstrap.android.ui.components.PrimaryButton
import com.rootstrap.android.ui.navActions
import com.rootstrap.android.ui.viewmodel.AuthViewModel

@Composable
fun SingUpScreen(viewModel: AuthViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditTextLayout(
            inputWrapperStateFlow = viewModel.name,
            onValueChange = viewModel::onNameEntered,
            keyboardType = KeyboardType.Text,
            label = context.getString(R.string.first_name)
        )
        EditTextLayout(
            inputWrapperStateFlow = viewModel.email,
            onValueChange = viewModel::onEmailEntered,
            keyboardType = KeyboardType.Email,
            label = context.getString(R.string.email)
        )
        EditTextLayout(
            inputWrapperStateFlow = viewModel.password,
            onValueChange = viewModel::onPasswordEntered,
            keyboardType = KeyboardType.Password,
            label = context.getString(R.string.password)
        )
        PrimaryButton(
            modifier = Modifier
                .padding(0.dp)
                .padding(Spacing.l),
            onClick = viewModel::onSignUp,
            isEnableStateFlow = viewModel.areInputsValid,
            text = context.getString(R.string.sign_up)
        )
        TextButton(
            modifier = Modifier
                .padding(0.dp)
                .padding(Spacing.l),
            onClick = { navActions.navigateTo(MainDestinations.ROUTE_SIGN_IN) },
            content = { Text(context.getString(R.string.got_account)) }
        )
    }
}
