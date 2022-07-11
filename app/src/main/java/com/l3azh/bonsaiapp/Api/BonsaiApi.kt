package com.l3azh.bonsaiapp.Api

import com.l3azh.bonsaiapp.Api.Request.*
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

    @POST(value = "tree-type/create-tree-type")
    suspend fun createNewTreeType(
        @Header(value = "Authorization") bearerToken: String,
        @Body request:CreateTreeTypeRequest
    ):Response<CreateTreeTypeResponse>

    @PUT(value = "tree-type/update-tree-type")
    suspend fun updateTreeType(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "uuidTreeType") uuidTreeType:String,
        @Body request: UpdateTreeTypeRequest
    ):Response<UpdateTreeTypeResponse>

    @GET(value = "tree/get-all-tree")
    suspend fun getAllTree(
        @Header(value = "Authorization") bearerToken: String
    ):Response<GetAllTreeResponse>

    @POST(value = "tree/create-new-tree")
    suspend fun createNewTree(
        @Header(value = "Authorization") bearerToken: String,
        @Body request:CreateTreeRequest
    ):Response<CreateTreeResponse>
}