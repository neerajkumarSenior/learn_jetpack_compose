package com.learn.commpose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.commpose.api_utils.TokenManager
import com.learn.commpose.screens.auth.ForgotPasswordScreen
import com.learn.commpose.screens.auth.LoginScreen
import com.learn.commpose.screens.auth.RegisterScreen
import com.learn.commpose.screens.home.HomeScreen
import com.learn.commpose.screens.members.GymRegistrationScreen

@Composable

fun MainNavigation( tokenManager: TokenManager) {


    val navController = rememberNavController()

    // Check if the token exists globally
    val token = tokenManager.getToken()

    // Set the start destination based on the token's existence
    val startDestination = if (token != null) Dest.Home else Dest.Login


    NavHost(navController = navController, startDestination = startDestination) {

        composable<Dest.Login> {

            LoginScreen(navController)


        }

        composable<Dest.Home> {

            HomeScreen(navController)

         // UserScreen(navController)


        }

        composable<Dest.Register> {
            RegisterScreen(navController)
        }

        composable<Dest.ForgotPassword> {
            ForgotPasswordScreen(navController)
        }


        composable<Dest.GymRegistrationScreen> {
            GymRegistrationScreen(navController)
        }
    }


}




