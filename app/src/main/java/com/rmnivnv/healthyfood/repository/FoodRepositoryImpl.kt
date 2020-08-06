package com.rmnivnv.healthyfood.repository

import com.rmnivnv.api.data.Event
import com.rmnivnv.api.repository.FoodRepository
import com.rmnivnv.api.LifesumApi
import com.rmnivnv.api.data.Food
import com.rmnivnv.api.response.FoodFailureResponse
import com.rmnivnv.api.response.FoodSuccessResponse
import com.rmnivnv.healthyfood.utils.FoodIdGenerator
import java.lang.Exception
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val networkApi: LifesumApi,
    private val foodIdGenerator: FoodIdGenerator
) : FoodRepository {

    override suspend fun getFood(): Event<Food> {
        return try {
            val response = networkApi.getFood(foodIdGenerator.generateId())
            if (response.isSuccessful) {
                response.body()?.let {
                    when (it) {
                        is FoodSuccessResponse -> Event.success(it.food)
                        is FoodFailureResponse -> Event.error(it.error)
                    }
                } ?: Event.error("Something went wrong")
            } else {
                Event.error(response.message())
            }
        } catch (e: Exception) {
            Event.error(e.message ?: e.toString())
        }
    }
}