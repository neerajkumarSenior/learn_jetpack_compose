package com.learn.commpose.api_utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject



class TokenManager @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "auth_token"
    }

    // यह मेथड टोकन सेट करेगा
    fun setToken(newToken: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, newToken).apply()
    }

    // यह मेथड टोकन प्राप्त करेगा
    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    // यह मेथड टोकन को हटा देगा
    fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }
}
