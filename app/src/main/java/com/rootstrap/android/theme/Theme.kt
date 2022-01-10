package com.rootstrap.android.theme

import androidx.annotation.NonNull
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.rootstrap.android.theme.colors.Dark
import com.rootstrap.android.theme.colors.Light


private val DarkThemeColors = darkColors(
    primary = Dark.primary,
    secondary = Dark.secondary,
    primaryVariant = Dark.primaryDark,
    background = Color.Yellow,
    onPrimary = Dark.onPrimary
    /* Other default colors to override
       surface = Color.White,
       onSecondary = Color.Black,
       onBackground = Color.Black,
       onSurface = Color.Black,
     */
)

private val LightThemeColors = lightColors(
    primary = Light.primary,
    secondary = Light.secondary,
    primaryVariant = Light.primaryDark,
    background = Color.White,
    onPrimary = Light.onPrimary,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun AppTheme(isDarkTheme: Boolean = false, @NonNull content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isDarkTheme) DarkThemeColors else LightThemeColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
