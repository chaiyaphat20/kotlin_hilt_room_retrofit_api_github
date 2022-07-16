package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network

import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoriesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceApi {
    @GET("repositories")
    suspend fun getDateFromAPI(@Query("q") query: String): Response<RepositoriesList>
}