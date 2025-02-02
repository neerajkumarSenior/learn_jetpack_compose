package com.learn.commpose.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.learn.commpose.api_utils.TokenManager
import com.learn.commpose.navigation.Dest
import com.learn.commpose.repository.auth.AuthRepository
import com.learn.commpose.useCases.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Sealed class for navigation events
sealed class NavigationEvent {
    object NavigateToLogin : NavigationEvent()
    // अन्य नेविगेशन इवेंट्स यहाँ जोड़ें
}

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState


    private val _logoutState = MutableStateFlow<Boolean>(false)
    val logoutState: StateFlow<Boolean> get() = _logoutState


    // Navigation events as a StateFlow
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> get() = _navigationEvents

    fun login(userId: String, password: String) {
        _loginState.value = LoginState.Loading

        // Launch a coroutine in the viewModelScope
        viewModelScope.launch {
            try {
                val response = repository.loginUser(userId, password)

                if (response != null && response.success) {
                    val  token =  response.data.token
                    _loginState.value = LoginState.Success(token ?: "")


                } else {
                    _loginState.value = LoginState.Error(response?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                // Handle any exceptions that occur during the network call
                _loginState.value = LoginState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // लॉगआउट फ़ंक्शन जो ViewModel से लॉगआउट करता है
//    fun logout() {
//        viewModelScope.launch {
//            val isLoggedOut = repository.logoutUser()
//            _logoutState.value = isLoggedOut
//
//
//        }
//    }

    fun logout() {
        viewModelScope.launch {
            val isLoggedOut = repository.logoutUser()
            if (isLoggedOut) {
                _logoutState.value = true
                _navigationEvents.emit(NavigationEvent.NavigateToLogin)
            }
        }
    }


    fun clearLogoutState() {
        _logoutState.value = false
    }
}
