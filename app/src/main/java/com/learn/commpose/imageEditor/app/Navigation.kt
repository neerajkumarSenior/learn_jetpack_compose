package com.learn.commpose.imageEditor.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController, viewModel)
        }
        composable("editScreen") {
            EditScreen(viewModel)
        }
    }
}