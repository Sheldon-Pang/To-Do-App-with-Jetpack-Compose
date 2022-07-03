package com.example.to_doappwithjetpackcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doappwithjetpackcompose.util.Action
import com.example.to_doappwithjetpackcompose.util.Constants
import com.example.to_doappwithjetpackcompose.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_doappwithjetpackcompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreens: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

    }
}