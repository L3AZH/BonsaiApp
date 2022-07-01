package com.l3azh.bonsaiapp.Api.Request

data class SignUpRequest(
    val email:String,
    val firstName:String,
    val lastName:String,
    val phoneNumber:String,
    val password:String
)