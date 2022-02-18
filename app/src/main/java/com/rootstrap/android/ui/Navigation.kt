package com.rootstrap.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rootstrap.android.ui.screens.main.HomeScreen
import com.rootstrap.android.ui.screens.onboarding.SingInScreen
import com.rootstrap.android.ui.screens.onboarding.SingUpScreen

//Add navigation logic here

object MainDestinations {
    const val ROUTE_SIGN_IN = "sign_in"
    const val ROUTE_SIGN_UP = "sign_up"
    const val ROUTE_HOME = "home"
}

lateinit var navActions: MainActions

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.ROUTE_SIGN_UP
) {
    navActions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.ROUTE_HOME) { HomeScreen() }
        composable(MainDestinations.ROUTE_SIGN_UP) { SingUpScreen() }
        composable(MainDestinations.ROUTE_SIGN_IN) { SingInScreen() }
    }
}

class MainActions(private val navController: NavHostController) {
    fun navigateTo(route: String, clear: Boolean = false) {
        with(navController) {
            if (clear) clearBackStack(route) else navigate(route)
        }
    }

    fun navigateUp() = navController.navigateUp()
}