package com.l3azh.bonsaiapp.Api

import com.l3azh.bonsaiapp.Api.Request.LoginRequest
import com.l3azh.bonsaiapp.Api.Request.SignUpRequest
import com.l3azh.bonsaiapp.Api.Response.*
import retrofit2.Response
import retrofit2.http.*

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

    @GET(value = "account/info")
    suspend fun getInfoAccount(
        @Header(value = "Authorization") bearerToken:String,
        @Query(value = "email") email:String
    ):Response<InfoAccountResponse>

    @GET(value = "tree-type/get-all-tree-type")
    suspend fun getAllTreeType(
        @Header(value = "Authorization") bearerToken: String
    ):Response<GetAllTreeTypeResponse>

    @GET(value = "tree/get-all-tree")
    suspend fun getAllTree(
        @Header(value = "Authorization") bearerToken: String
    ):Response<GetAllTreeResponse>
}