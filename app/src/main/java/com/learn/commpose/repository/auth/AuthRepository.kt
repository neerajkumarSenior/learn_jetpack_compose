package com.learn.commpose.repository.auth

import com.learn.commpose.api_utils.TokenManager
import com.learn.commpose.apis.UserApi
import com.learn.commpose.model.LoginRequest
import com.learn.commpose.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: UserApi,
    private val tokenManager: TokenManager
) {

    // लॉगिन फ़ंक्शन जो यूज़र आईडी और पासवर्ड लेता है
    suspend fun loginUser(userId: String, password: String): LoginResponse? {
        // लॉगिन अनुरोध बनाने के लिए LoginRequest डेटा क्लास का उपयोग करें
        val loginRequest = LoginRequest(userId, password)
        return try {
            // API कॉल करें और प्रतिक्रिया प्राप्त करें
            val response = api.loginUser(loginRequest)

            // यदि प्रतिक्रिया सफल है, तो LoginResponse लौटाएं
            if (response.isSuccessful) {
                val loginResponse = response.body() // यहाँ LoginResponse को वापस करें

                // Check if loginResponse is not null and contains the token
                loginResponse?.let {
                    // Assuming the token is part of the LoginResponse
                    val token = it.data.token
                    // Save the token using TokenManager
                    tokenManager.setToken(token)
                }

                loginResponse // Return the LoginResponse
            } else {
                null // यदि प्रतिक्रिया सफल नहीं है तो null लौटाएं
            }
        } catch (e: Exception) {
            null // किसी भी प्रकार की त्रुटि के लिए null लौटाएं
        }
    }



    // लॉगआउट फ़ंक्शन
    suspend fun logoutUser(): Boolean {
        return try {
            // Clear the token from SharedPreferences
            tokenManager.clearToken()
            true // Return true to indicate successful logout
        } catch (e: Exception) {
            false // Return false if any error occurs while clearing the token
        }
    }
}
