package com.rmnivnv.api

import com.rmnivnv.api.response.FoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LifesumApi {
    @GET("v2/foodipedia/codetest")
    suspend fun getFood(@Query("foodid") id: Int): Response<FoodResponse>
}