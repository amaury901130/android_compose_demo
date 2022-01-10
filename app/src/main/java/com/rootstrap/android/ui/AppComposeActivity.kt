package com.rootstrap.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.rootstrap.android.R
import com.rootstrap.android.ui.base.BaseActivity
import com.rootstrap.android.ui.onboarding.SingUpForm
import com.rootstrap.android.ui.viewmodel.SignUpViewModel
import com.rootstrap.android.util.NetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppComposeActivity : BaseActivity() {

    @Composable
    override fun ComposableView() {
        ObserveNetwork()
        SingUpForm()
    }

    @Composable
    fun ObserveNetwork(baseViewModel: SignUpViewModel = hiltViewModel()) {
        val inputWrapperState by baseViewModel.networkState.collectAsState()
        when (inputWrapperState) {
            NetworkState.loading -> showProgress()
            NetworkState.idle -> hideProgress()
            else -> showError(baseViewModel.error ?: getString(R.string.default_error))
        }
    }
}
