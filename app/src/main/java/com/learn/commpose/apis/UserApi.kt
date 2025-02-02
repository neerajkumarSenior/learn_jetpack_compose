package com.learn.commpose.apis

import com.learn.commpose.model.LoginRequest
import com.learn.commpose.model.LoginResponse
import com.learn.commpose.model.RegisterRequest
import com.learn.commpose.model.UserDetailsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    // Endpoint for user registration
    @POST("customer/registration")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterRequest>

    // Endpoint for user login
    @POST("customer/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>


    // Endpoint to get user details
    @GET("user/{id}")
    fun getUserDetails(@Path("id") userId: String): Call<UserDetailsResponse>

    // Other user-related endpoints can be added here
}
