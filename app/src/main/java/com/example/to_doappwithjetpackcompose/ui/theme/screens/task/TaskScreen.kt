package com.example.to_doappwithjetpackcompose.ui.theme.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.to_doappwithjetpackcompose.data.models.Priority
import com.example.to_doappwithjetpackcompose.data.models.ToDoTask
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModels: SharedViewModels,
    navigateToListScreens: (Action) -> Unit
) {
    val title: String by sharedViewModels.title
    val description: String by sharedViewModels.description
    val priority: Priority by sharedViewModels.priority

    val context = LocalContext.current

    BackHandler(onBackPressed = { navigateToListScreens(Action.NO_ACTION) })

    Scaffold(
        topBar = {
             TaskAppBar(
                 selectedTask = selectedTask,
                 navigateToListScreens = { action ->
                     if (action == Action.NO_ACTION) {
                         navigateToListScreens(action)
                     } else {
                         if (sharedViewModels.validateFields()) {
                             navigateToListScreens(action)
                         } else {
                             displayToast(context = context)
                         }
                     }
                 }
             )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModels.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModels.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModels.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields can not be Empty.",
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun BackHandler(
    backDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallBack = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backDispatcher) {
        backDispatcher?.addCallback(backCallBack)

        onDispose {
            backCallBack.remove()
        }
    }
}
