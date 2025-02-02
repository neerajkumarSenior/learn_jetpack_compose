package com.learn.commpose.screens.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.learn.commpose.navigation.Dest
import com.learn.commpose.useCases.LoginState
import com.learn.commpose.viewModels.AuthViewModel

@Composable
fun LoginScreen(navController: NavController) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val   viewModel: AuthViewModel = hiltViewModel()
    // Collect the login state
    val loginState by viewModel.loginState.collectAsState()



    val chatGptGradient =  Brush.linearGradient(
        colors = listOf(
            // Deep Black
            Color(0xFFFFD1D1),   Color(0xFFFFD1D1),   Color(0xFFFFD1D1),
            Color(0xFFFFD1D1),
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f),
        tileMode = TileMode.Mirror
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = chatGptGradient) // Apply the gradient background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
           viewModel.login(userId, password)
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show login state (loading, success, or error)
        // Add Register and Forgot Password buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {
                // Navigate to Register Screen
                navController.navigate(Dest.Register)
            }) {
                Text("Register")
            }

            TextButton(onClick = {
                // Navigate to Forgot Password Screen
                navController.navigate(Dest.ForgotPassword)
            }) {
                Text("Forgot Password?")
            }
        }

        when (loginState) {
            is LoginState.Loading -> {
                // Loading UI
                CircularProgressIndicator()
            }
            is LoginState.Success -> {

                LaunchedEffect(loginState) {
                    // Set popUpTo to remove login screen from back stack
                    navController.navigate(Dest.Home) {
                        popUpTo(Dest.Login) { inclusive = true }
                    }
                }




            }
            is LoginState.Error -> {

                val errorMessage = (loginState as LoginState.Error).message
                Text(text = errorMessage, color = Color.Red) // त्रुटि संदेश दिखाएं

            }
            else -> {
                // Initial state or Idle
            }
        }

    }}
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
   // LoginScreen()
}
