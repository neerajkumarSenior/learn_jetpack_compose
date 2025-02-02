package com.learn.commpose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.learn.commpose.navigation.Dest
import com.learn.commpose.viewModels.AuthViewModel
import com.learn.commpose.viewModels.NavigationEvent

@Composable
fun AccountScreen(navController: NavController){

    val authViewModel: AuthViewModel = hiltViewModel()
    val navigationEvent by authViewModel.navigationEvents.collectAsState(initial = null)


    // Listen for navigation events
    LaunchedEffect(navigationEvent) {
        navigationEvent?.let {
            when (it) {
                is NavigationEvent.NavigateToLogin -> {
                    navController.navigate(Dest.Login) {
                        popUpTo(Dest.Home) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }


Column(
modifier = Modifier
.fillMaxSize(),

horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center
) {
    Text("Welcome to the Home Screen")

        Button(onClick = { authViewModel.logout() }) {
            Text("Logout")
        }
}



}