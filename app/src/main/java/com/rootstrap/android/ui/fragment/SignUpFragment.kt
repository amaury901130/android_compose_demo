package com.rootstrap.android.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.fragment.app.viewModels
import com.rootstrap.android.R
import com.rootstrap.android.theme.Spacing
import com.rootstrap.android.ui.base.BaseFragment
import com.rootstrap.android.ui.components.EditTextLayout
import com.rootstrap.android.ui.components.PrimaryButton
import com.rootstrap.android.ui.viewmodel.SignUpActivityViewModel
import com.rootstrap.android.ui.viewmodel.SignUpState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    private val viewModel: SignUpActivityViewModel by viewModels()

    @Composable
    override fun ComposableView() {
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
                text = getString(R.string.sign_up)
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        setObservers()
    }

    private fun setObservers() {
        with(viewModel) {
            state.observe(requireActivity(), {
                when (it) {
                    SignUpState.signUpFailure -> showError(error)
                    SignUpState.signUpSuccess -> navigateTo(R.id.nav_onboarding_to_main)
                }
            })
            observeNetwork(this)
        }
    }
}
