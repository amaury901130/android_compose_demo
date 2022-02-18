package com.rootstrap.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.rootstrap.android.R
import com.rootstrap.android.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("Registered")
@AndroidEntryPoint
open class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContent {
            AppTheme {
                AppNavGraph()
            }
        }
    }
}
