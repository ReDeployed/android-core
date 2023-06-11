package com.redeploy.coreViewer.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.72.128:8080"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DatabaseApiService {
    @GET("/pingDB")
    suspend fun testDB(): String

    @GET("/listApp")
    suspend fun listApp(): String
}

object DatabaseApi {
    val retrofitService : DatabaseApiService by lazy {
        retrofit.create(DatabaseApiService::class.java)
    }

}
