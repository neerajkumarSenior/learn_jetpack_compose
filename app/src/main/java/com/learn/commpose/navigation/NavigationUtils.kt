package com.learn.commpose.navigation

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

sealed class Dest {
    @Serializable
    data object Login : Dest()

    @Serializable
    data object Home : Dest()


    @Serializable
    data object Register : Dest()

    @Serializable
    data object ForgotPassword : Dest()

    @Serializable
    data object GymRegistrationScreen : Dest()







}