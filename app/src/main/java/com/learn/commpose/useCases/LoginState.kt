package com.learn.commpose.useCases

sealed class LoginState {
    object Idle : LoginState() // Initial state
    object Loading : LoginState() // Loading state while logging in
    data class Success(val token: String) : LoginState() // Successful login state
    data class Error(val message: String) : LoginState() // Error state with a message
}
