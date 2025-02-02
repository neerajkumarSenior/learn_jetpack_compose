package com.learn.commpose.api_utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = tokenManager.getToken()



        // नए अनुरोध में ऑथराइजेशन हेडर जोड़ें
        val requestWithAuth = originalRequest.newBuilder()

            .header("Authorization", "Bearer $token")
            .build()

        // अगले इंटरसेप्टर या सर्वर को नया अनुरोध भेजें
        return chain.proceed(requestWithAuth)
    }
}
