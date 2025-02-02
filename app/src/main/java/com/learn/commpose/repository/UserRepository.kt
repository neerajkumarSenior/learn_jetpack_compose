package com.learn.commpose.repository

import com.learn.commpose.apis.ApiService
import com.learn.commpose.model.Post
import com.learn.commpose.model.UserRequest

import com.learn.commpose.network.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject






class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getUsers(userId: String): Flow<ApiResult<List<Post>>> = flow {
        emit(ApiResult.Loading)

        try {
            val userRequest = UserRequest(userId)
            val response = apiService.getUsers(userRequest)

            if (response.success) {
                emit(ApiResult.Success(response.data ?: emptyList()))
            } else {
                emit(ApiResult.Error(response.message))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Unknown error"))
        }
    }
}





//class UserRepository @Inject constructor(
//    private val apiService: ApiService
//) {
//    suspend fun getUsers(userId: String): ApiResult<List<Post>> {
//        return try {
//            val userRequest = UserRequest(userId)
//            val response = apiService.getUsers(userRequest)
//
//            if (response.success) {
//                ApiResult.Success(response.data ?: emptyList())
//            } else {
//                ApiResult.Error(response.message)
//            }
//        } catch (e: Exception) {
//            ApiResult.Error(e.message ?: "Unknown error")
//        }
//    }
//}
