package com.rootstrap.android.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rootstrap.android.R
import com.rootstrap.android.theme.AppTheme
import com.rootstrap.android.util.LoadingDialogUtil
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("Registered")
@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_Light_DarkActionBar)
        setContent {
            AppTheme {
                ComposableView()
            }
        }
    }

    @Composable
    open fun ComposableView() = Text(text = "Hello world.")

    fun showProgress() {
        LoadingDialogUtil.showProgress(this)
    }

    fun hideProgress() {
        LoadingDialogUtil.hideProgress()
    }

    fun showError(message: String?) {
        LoadingDialogUtil.showError(message, this)
    }
}
