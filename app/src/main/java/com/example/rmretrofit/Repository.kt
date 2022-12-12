package com.example.rmretrofit

import com.example.rmretrofit.network.ApiService
import com.example.rmretrofit.network.CharacterResponse
import retrofit2.Call
import retrofit2.http.Query

class Repository (private val apiService: ApiService) {
    suspend fun getCharacters (page : String) = apiService.fetchCharacters(page)

}