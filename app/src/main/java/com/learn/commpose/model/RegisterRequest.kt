package com.learn.commpose.model

// Data class for user registration request
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)


// Data class for user login request
data class LoginRequest(
    val user_id: String,
    val password: String
)




data class LoginResponse(
    val success: Boolean,  // Add this field
    val message: String,   // Add this field for the message
    val data: UserData     // Change to a UserData object
)

data class UserData(
    val token: String,
    val id: Int,
    val name: String,
    val user_id: String
)


// Data class for user details response
data class UserDetailsResponse(
    val id: String,
    val username: String,
    val email: String,
    val createdAt: String
)
