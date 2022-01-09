package com.rootstrap.android.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.rootstrap.android.R
import com.rootstrap.android.ui.viewmodel.SignUpActivityViewModel
import com.rootstrap.android.ui.viewmodel.SignUpState
import com.rootstrap.android.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    private val viewModel: SignUpActivityViewModel by viewModels()

    @Composable
    override fun ComposableView() {
        Text(text = "Hello world.")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        setObservers()
    }

    private fun setUpView() {

    }

    private fun signUp() {

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
