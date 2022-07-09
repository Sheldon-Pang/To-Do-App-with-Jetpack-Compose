package com.example.to_doappwithjetpackcompose.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doappwithjetpackcompose.ui.theme.screens.task.TaskScreen
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Action
import com.example.to_doappwithjetpackcompose.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_doappwithjetpackcompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModels: SharedViewModels,
    navigateToListScreens: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModels.getSelectedTask(taskId = taskId)
        }
        val selectedTask by sharedViewModels.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModels.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModels = sharedViewModels,
            navigateToListScreens = navigateToListScreens
        )
    }
}