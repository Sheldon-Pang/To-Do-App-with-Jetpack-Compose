package com.example.to_doappwithjetpackcompose.navigation

import androidx.navigation.NavController
import com.example.to_doappwithjetpackcompose.util.Action
import com.example.to_doappwithjetpackcompose.util.Constants.LIST_SCREEN
import com.example.to_doappwithjetpackcompose.util.Constants.SPLASH_SCREEN

class Screens(navController: NavController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val list: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }
}