package com.example.to_doappwithjetpackcompose.ui.theme.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doappwithjetpackcompose.R
import com.example.to_doappwithjetpackcompose.ui.theme.fabBackgroundColor
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import com.example.to_doappwithjetpackcompose.util.SearchAppBarState

@Composable
fun ListScreen(
    navigateToTaskScreens: (taskId: Int) -> Unit,
    sharedViewModels: SharedViewModels
) {
    val searchAppBarState: SearchAppBarState
            by sharedViewModels.searchAppBarState
    val searchTextState: String
            by sharedViewModels.searchTextState

    Scaffold(
        topBar = {
                 ListAppBar(
                     sharedViewModels = sharedViewModels,
                     searchAppBarState = searchAppBarState,
                     searchTextState = searchTextState
                 )
        },
        content = {
                  ListContent()
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

//@Composable
//@Preview
//private fun ListScreenPreview() {
//    ListScreen(navigateToTaskScreens = {})
//}