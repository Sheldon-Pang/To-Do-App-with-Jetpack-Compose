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
import kotlin.math.log

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreens: (taskId: Int) -> Unit,
    sharedViewModels: SharedViewModels
) {
    LaunchedEffect(key1 = true) {
        sharedViewModels.getAllTasks()
    }

    val action by sharedViewModels.action

    val allTask by sharedViewModels.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState
            by sharedViewModels.searchAppBarState
    val searchTextState: String
            by sharedViewModels.searchTextState

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { sharedViewModels.handleDatabaseActions(action = action) },
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
                      tasks = allTask,
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
    taskTitle: String,
    action: Action
) {
   handleDatabaseAction()

   val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = "ok"
                )
            }
        }
    }
}