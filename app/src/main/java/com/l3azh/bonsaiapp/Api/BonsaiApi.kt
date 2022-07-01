package com.l3azh.bonsaiapp.Api

import com.l3azh.bonsaiapp.Api.Request.LoginRequest
import com.l3azh.bonsaiapp.Api.Request.SignUpRequest
import com.l3azh.bonsaiapp.Api.Response.BonsaiResponse
import com.l3azh.bonsaiapp.Api.Response.LoginResponse
import com.l3azh.bonsaiapp.Api.Response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BonsaiApi {

    @POST(value = "auth/login")
    suspend fun login(
        @Header(value = "Authorization") basicAuth:String,
        @Body request: LoginRequest
    ):Response<LoginResponse>

    @POST(value = "auth/signup")
    suspend fun signUp(
        @Header(value = "Authorization") basicAuth: String,
        @Body request: SignUpRequest
    ):Response<SignUpResponse>
}