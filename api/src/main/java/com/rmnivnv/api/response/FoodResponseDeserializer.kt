package com.rmnivnv.api.response

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rmnivnv.api.data.Food
import com.rmnivnv.api.data.Meta
import java.lang.reflect.Type

private const val MEMBER_META = "meta"
private const val MEMBER_RESPONSE = "response"
private const val CODE_SUCCESS = 200
private const val ERROR_DEFAULT = "Something went wrong"

class FoodResponseDeserializer : JsonDeserializer<FoodResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): FoodResponse = with(json.asJsonObject) {
        if (has(MEMBER_META)) {
            extractFromMeta(this, context)
        } else {
            FoodFailureResponse(ERROR_DEFAULT)
        }
    }

    private fun extractFromMeta(
        jsonObject: JsonObject,
        context: JsonDeserializationContext
    ): FoodResponse = with(jsonObject) {
        val meta = context.deserialize<Meta>(get(MEMBER_META), Meta::class.java)
        if (meta.code == CODE_SUCCESS) {
            val food = context.deserialize<Food>(get(MEMBER_RESPONSE), Food::class.java)
            FoodSuccessResponse(food)
        } else {
            FoodFailureResponse(meta.errorDetail)
        }
    }
}