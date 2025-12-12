package com.appweek14.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    // https://randomuser.me/api/?results=2
    @GET("api/?")
    suspend fun getRandomUsers(
        @Query("results") count: Int = 2
    ): RandomUserResponse
}