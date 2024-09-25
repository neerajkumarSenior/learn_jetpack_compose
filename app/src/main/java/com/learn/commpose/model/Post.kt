package com.learn.commpose.model

data class Post(
    var user_id: String,
    val name: String,
    val phone: String,
    val createdAt: String,
    val activatedDate: String,
    val sponsorId: String,
    val status: String,
    val sponsorName: String
)
