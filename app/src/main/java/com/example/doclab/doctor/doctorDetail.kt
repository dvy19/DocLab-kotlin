package com.example.doclab.doctor

data class doctorDetail(
    val uid: String = "",
    val age: Int = 0,
    val phoneNumber: String = "",
    val gender:String="",
    val createdAt: Long = System.currentTimeMillis()

)
