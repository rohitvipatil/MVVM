package com.example.rmretrofit.network

import android.os.Build.VERSION_CODES.BASE
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}


interface ApiService {
    @GET("character")
   suspend fun fetchCharacters (@Query("page")page : String):CharacterResponse
}