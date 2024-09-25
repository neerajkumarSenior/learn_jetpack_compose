package com.learn.commpose.network

import com.learn.commpose.model.Post


data class ApiResponse(
    val success: Boolean,
    val message: String,
    val data: List<Post>?
)
