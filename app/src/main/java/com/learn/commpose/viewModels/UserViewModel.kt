package com.learn.commpose.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.commpose.model.Post
import com.learn.commpose.model.UserRequest
import com.learn.commpose.network.ApiResult
import com.learn.commpose.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {


    // Sealed class state
    var userResult by mutableStateOf<ApiResult<List<Post>>>(ApiResult.Loading)
        private set

    init {

        var user_id = "PHC123456"
        fetchUsers(user_id)
    }

    private fun fetchUsers(user_id: String) {

        viewModelScope.launch {


            userResult = ApiResult.Loading

            try {

                val userRequest = UserRequest(user_id)


                val response = RetrofitInstance.api.getUsers(userRequest)


                if (response.success) {
                    userResult = ApiResult.Success(response.data?: emptyList())
                } else {
                    userResult = ApiResult.Error(response.message)
                }


            } catch (e: Exception) {
                userResult = ApiResult.Error(e.message ?: "Unknown error")

            }

        }




    }


}