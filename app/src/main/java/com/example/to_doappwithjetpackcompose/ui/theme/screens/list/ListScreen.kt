package com.example.to_doappwithjetpackcompose.ui.theme.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doappwithjetpackcompose.R

@Composable
fun ListScreen(navigateToTaskScreens: (Int) -> Unit) {
    Scaffold(
        content = {},
        floatingActionButton = {
            ListFab(navigateToTaskScreens = navigateToTaskScreens)
        }
    )
}

@Composable
fun ListFab(navigateToTaskScreens: (Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            navigateToTaskScreens(-1)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreens = {})
}