package com.example.to_doappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_doappwithjetpackcompose.navigation.SetupNavigation
import com.example.to_doappwithjetpackcompose.ui.theme.ToDoAppWithJetpackComposeTheme
import com.example.to_doappwithjetpackcompose.ui.theme.viewmodels.SharedViewModels
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModels: SharedViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppWithJetpackComposeTheme {
                navController = rememberNavController()
                SetupNavigation(
                    navController = navController,
                    sharedViewModels = sharedViewModels
                )
            }
        }
    }
}