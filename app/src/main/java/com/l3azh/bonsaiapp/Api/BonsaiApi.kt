package com.l3azh.bonsaiapp.Api

import com.l3azh.bonsaiapp.Api.Request.*
import com.l3azh.bonsaiapp.Api.Response.*
import retrofit2.Response
import retrofit2.http.*

interface BonsaiApi {

    /**
     * Authentication
     */
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

    @PUT(value = "account/update-info")
    suspend fun updateAccountInfo(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "email") email: String,
        @Body infoAccount:UpdateAccountRequest
    ):Response<UpdateAccountInfoResponse>

    /**
     * Tree Type
     */

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

    /**
     * Tree
     */
    @GET(value = "tree/get-all-tree")
    suspend fun getAllTree(
        @Header(value = "Authorization") bearerToken: String
    ):Response<GetAllTreeResponse>

    @GET(value = "tree/get-tree-by-name")
    suspend fun getTreeByName(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "nameTree") nameTree:String
    ):Response<GetTreeByNameResponse>

    @GET(value = "tree/get-tree")
    suspend fun getTreeInfo(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "uuidTree") uuidTree: String
    ):Response<GetTreeResponse>

    @POST(value = "tree/create-new-tree")
    suspend fun createNewTree(
        @Header(value = "Authorization") bearerToken: String,
        @Body request:CreateTreeRequest
    ):Response<CreateTreeResponse>

    @GET(value = "tree/get-all-tree-group-by-tree-type")
    suspend fun getAllTreeGroupByTreeType(
        @Header(value = "Authorization") bearerToken: String,
    ):Response<GetAllTreeGroupByTreeTypeResponse>

    @GET(value = "tree/get-tree-group-by-tree-type")
    suspend fun getTreeGroupByTreeType(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "uuidTreeType") uuidTreeType: String
    ):Response<GetTreeGroupByTreeTypeResponse>

    @PUT(value = "tree/update-tree")
    suspend fun updateTree(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "uuidTree") uuidTree:String,
        @Body request:UpdateTreeRequest
    ):Response<UpdateTreeResponse>

    /**
     * Bill
     */
    @POST(value = "bill/create-bill")
    suspend fun createBill(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "email") email:String,
        @Body request:CreateBillRequest
    ):Response<CreateBillResponse>

    @GET(value = "bill/get-bill-account")
    suspend fun getBillOfAccount(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "email") email: String
    ):Response<BillOfEmailResponse>

    @GET(value = "bill/get-all-bill")
    suspend fun getAllBill(
        @Header(value = "Authorization") bearerToken: String
    ):Response<AllBillResponse>

    @GET(value = "bill/get-bill-info")
    suspend fun getBillDetail(
        @Header(value = "Authorization") bearerToken: String,
        @Query(value = "uuidBill") uuidBill:String
    ):Response<BillInfoResponse>
}