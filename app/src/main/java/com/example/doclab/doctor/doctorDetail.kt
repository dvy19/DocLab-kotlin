package com.example.doclab.doctor

data class doctorDetail(
    val uid: String = "",
    val age: Int = 0,
    val phoneNumber: String = "",
    val gender:String="",
    val duration:String="",
    val about:String="",
    val fees:String="",
    val mode:String="",
    val createdAt: Long = System.currentTimeMillis()

)

data class doctorAddress(
    val hospitalName:String="",
    val clinic:String="",
    val city:String=""
)

data class doctorEducation(
    val specialisation:String="",
    val experience:String="",
    val qualification:String=""
)