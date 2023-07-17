package com.example.users.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val baseUrl = "https://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

object Data {
    val retrofitService : UsersApiService by lazy {
        retrofit.create(UsersApiService::class.java)
    }
}

interface UsersApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}