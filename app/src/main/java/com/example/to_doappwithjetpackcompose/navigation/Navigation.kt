package com.example.to_doappwithjetpackcompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_doappwithjetpackcompose.navigation.destinations.listComposable
import com.example.to_doappwithjetpackcompose.navigation.destinations.taskComposable
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Constants.LIST_SCREEN

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
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreens = screen.task,
            sharedViewModels = sharedViewModels
        )
        taskComposable(
            navigateToListScreens = screen.list,
            sharedViewModels = sharedViewModels
        )
    }
}