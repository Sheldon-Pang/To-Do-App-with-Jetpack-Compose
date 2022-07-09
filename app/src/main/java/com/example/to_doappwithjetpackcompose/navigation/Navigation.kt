package com.example.to_doappwithjetpackcompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_doappwithjetpackcompose.navigation.destinations.listComposable
import com.example.to_doappwithjetpackcompose.navigation.destinations.splashComposable
import com.example.to_doappwithjetpackcompose.navigation.destinations.taskComposable
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Constants.SPLASH_SCREEN

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModels: SharedViewModels
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreens = screen.list,
            sharedViewModels = sharedViewModels
        )
        taskComposable(
            navigateToListScreens = screen.task,
            sharedViewModels = sharedViewModels
        )
    }
}