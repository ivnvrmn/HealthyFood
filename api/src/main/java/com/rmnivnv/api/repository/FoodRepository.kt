package com.rmnivnv.api.repository

import com.rmnivnv.api.data.Event
import com.rmnivnv.api.data.Food

interface FoodRepository {
    suspend fun getFood(): Event<Food>
}