package com.learn.commpose.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.commpose.model.Post
import com.learn.commpose.network.ApiResult
import com.learn.commpose.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject





@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // Expose StateFlow instead of MutableState
    private val _userResult = MutableStateFlow<ApiResult<List<Post>>>(ApiResult.Loading)
    val userResult: StateFlow<ApiResult<List<Post>>> = _userResult

    init {
        val userId = "PHC123456"
        fetchUsers(userId)
    }

    private fun fetchUsers(userId: String) {
        viewModelScope.launch {
            userRepository.getUsers(userId).collect { result ->
                _userResult.value = result
            }
        }
    }
}
















//@HiltViewModel
//class UserViewModel @Inject constructor(
//    private val userRepository: UserRepository
//) : ViewModel() {
//
//    var userResult by mutableStateOf<ApiResult<List<Post>>>(ApiResult.Loading)
//        private set
//
//    init {
//        val userId = "PHC123456"
//        fetchUsers(userId)
//    }
//
//    private fun fetchUsers(userId: String) {
//        viewModelScope.launch {
//            userResult = ApiResult.Loading
//
//            val result = userRepository.getUsers(userId)
//
//            userResult = result
//        }
//    }
//}
