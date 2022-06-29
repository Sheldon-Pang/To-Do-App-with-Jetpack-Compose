package com.example.to_doappwithjetpackcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doappwithjetpackcompose.navigation.Screens
import com.example.to_doappwithjetpackcompose.ui.theme.screens.list.ListScreen
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_doappwithjetpackcompose.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreens: (taskId: Int) -> Unit,
    sharedViewModels: SharedViewModels
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(
            navigateToTaskScreens = navigateToTaskScreens,
            sharedViewModels = sharedViewModels
        )
    }
}