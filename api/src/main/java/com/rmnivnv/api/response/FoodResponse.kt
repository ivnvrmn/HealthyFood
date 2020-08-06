package com.rmnivnv.api.response

import com.google.gson.annotations.JsonAdapter
import com.rmnivnv.api.data.Food

@JsonAdapter(FoodResponseDeserializer::class)
sealed class FoodResponse

data class FoodSuccessResponse(
    val food: Food
) : FoodResponse()

data class FoodFailureResponse(
    val error: String
) : FoodResponse()