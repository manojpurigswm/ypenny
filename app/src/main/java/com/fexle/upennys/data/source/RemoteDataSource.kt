package com.fexle.upennys.data.source

import com.fexle.upennys.data.source.response.BaseResponse
import com.fexle.upennys.helper.URLHelper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RemoteDataSource {


    @POST(URLHelper.USER_AUTH)
    suspend fun authenticateUser(@Body body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>

    @POST(URLHelper.USER_REGISTER)
    @Multipart
    suspend fun registerUser(@PartMap body: LinkedHashMap<String, RequestBody>): Response<BaseResponse<Any>>


    @POST(URLHelper.USER_LOGIN)
    suspend fun loginUser(@Body body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>


    @POST(URLHelper.SEND_OTP)
    suspend fun sendOtp(@Body body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>


    @POST(URLHelper.VERIFY_OTP)
    suspend fun verifyOtp(@Body body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>


    @POST(URLHelper.RESET_PASSWORD)
    suspend fun resetPassword(@Body body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>


    @GET(URLHelper.GET_PROFILE)
    suspend fun getProfile(@QueryMap body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>


    @PUT(URLHelper.UPDATE_PROFILE)
    @Multipart
    suspend fun updateProfile(@PartMap body: LinkedHashMap<String, RequestBody>, @Part images: List<MultipartBody.Part>): Response<BaseResponse<Any>>


    @PUT(URLHelper.CHANGE_PASSWORD)
    suspend fun changePassword(@Body body: LinkedHashMap<String, String>): Response<BaseResponse<Any>>



}