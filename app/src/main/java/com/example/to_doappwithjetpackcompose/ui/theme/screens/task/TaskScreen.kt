package com.example.to_doappwithjetpackcompose.ui.theme.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.to_doappwithjetpackcompose.util.Action

@Composable
fun TaskScreen(
    navigateToListScreens: (Action) -> Unit
) {
    Scaffold(
        topBar = {
             TaskAppBar(
                 navigateToListScreens = navigateToListScreens
             )
        },
        content = {

        }
    )
}