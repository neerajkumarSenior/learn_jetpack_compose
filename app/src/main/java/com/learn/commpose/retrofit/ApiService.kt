package com.learn.commpose.retrofit

import com.learn.commpose.model.UserRequest
import com.learn.commpose.network.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("view-direct")
    suspend fun getUsers(@Body userRequest: UserRequest): ApiResponse
}
