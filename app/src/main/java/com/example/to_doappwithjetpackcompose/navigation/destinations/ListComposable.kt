package com.example.to_doappwithjetpackcompose.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doappwithjetpackcompose.ui.theme.screens.list.ListScreen
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_doappwithjetpackcompose.util.Constants.LIST_SCREEN
import com.example.to_doappwithjetpackcompose.util.toAction

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreens: (taskId: Int) -> Unit,
    sharedViewModels: SharedViewModels
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModels.action.value = action
        }

        ListScreen(
            navigateToTaskScreens = navigateToTaskScreens,
            sharedViewModels = sharedViewModels
        )
    }
}