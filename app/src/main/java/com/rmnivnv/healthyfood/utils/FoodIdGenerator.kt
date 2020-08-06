package com.rmnivnv.healthyfood.utils

interface FoodIdGenerator {
    suspend fun generateId(): Int
}