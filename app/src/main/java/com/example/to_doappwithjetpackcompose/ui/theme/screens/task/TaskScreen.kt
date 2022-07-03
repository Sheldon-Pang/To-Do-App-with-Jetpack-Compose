package com.example.to_doappwithjetpackcompose.ui.theme.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

    Scaffold(
        topBar = {
             TaskAppBar(
                 selectedTask = selectedTask,
                 navigateToListScreens = navigateToListScreens
             )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModels.title.value = it
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