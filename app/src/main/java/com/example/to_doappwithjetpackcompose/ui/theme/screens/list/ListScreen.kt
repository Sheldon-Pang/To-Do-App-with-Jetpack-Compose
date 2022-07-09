package com.example.to_doappwithjetpackcompose.ui.theme.screens.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.to_doappwithjetpackcompose.R
import com.example.to_doappwithjetpackcompose.ui.theme.fabBackgroundColor
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Action
import com.example.to_doappwithjetpackcompose.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreens: (taskId: Int) -> Unit,
    sharedViewModels: SharedViewModels
) {
    LaunchedEffect(key1 = true) {
        sharedViewModels.getAllTasks()
         sharedViewModels.readSortState()
    }

    val action by sharedViewModels.action

    val allTask by sharedViewModels.allTasks.collectAsState()
    val searchedTasks by sharedViewModels.searchTasks.collectAsState()
    val sortState by sharedViewModels.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModels.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModels.highPriorityTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModels.searchAppBarState
    val searchTextState: String by sharedViewModels.searchTextState

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { sharedViewModels.handleDatabaseActions(action = action) },
        onUndoClicked = {
            sharedViewModels.action.value = it
        },
        taskTitle = sharedViewModels.title.value,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 ListAppBar(
                     sharedViewModels = sharedViewModels,
                     searchAppBarState = searchAppBarState,
                     searchTextState = searchTextState
                 )
        },
        content = {
                  ListContent(
                      allTasks = allTask,
                      searchedTasks = searchedTasks,
                      lowPriorityTasks = lowPriorityTasks,
                      highPriorityTasks = highPriorityTasks,
                      sortState = sortState,
                      searchAppBarState = searchAppBarState,
                      onSwipeToDelete = { action, task ->
                          sharedViewModels.action.value = action
                          sharedViewModels.updateTaskFields(selectedTask = task)
                      },
                      navigateToTaskScreens = navigateToTaskScreens
                  )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreens = navigateToTaskScreens)
        }
    )
}

@Composable
fun ListFab(navigateToTaskScreens: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            navigateToTaskScreens(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseAction: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
   handleDatabaseAction()

   val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when(action) {
        Action.DELETE_ALL -> "All Tasks Removed."
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask (
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }
}