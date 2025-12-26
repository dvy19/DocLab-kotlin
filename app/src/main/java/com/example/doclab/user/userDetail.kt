package com.example.doclab.user

data class userDetail(
    val uid: String = "",
    val age: Int = 0,
    val phoneNumber: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
