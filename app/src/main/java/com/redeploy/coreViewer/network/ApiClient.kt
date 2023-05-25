package com.redeploy.coreViewer.network

import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://URL/api/"

private val retroFit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @POST("auth")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse>

    @GET("devices")
    suspend fun getDevices(@Header("Authorization") token: String): Response<ApiResponse>
}

object MainApi {
    val apiServer : ApiService by lazy {
        retroFit.create(ApiService::class.java)
    }
}
