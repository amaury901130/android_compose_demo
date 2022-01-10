package com.rootstrap.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.rootstrap.android.R
import com.rootstrap.android.theme.AppTheme
import com.rootstrap.android.util.LoadingDialogUtil
import com.rootstrap.android.util.NetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                ComposableView()
            }
        }
    }

    @Composable
    open fun ComposableView() = Text(text = "Hello world.")

    private fun showProgress() {
        LoadingDialogUtil.showProgress(requireContext())
    }

    private fun hideProgress() {
        LoadingDialogUtil.hideProgress()
    }

    fun showError(message: String?) {
        LoadingDialogUtil.showError(message, requireContext())
    }
}
