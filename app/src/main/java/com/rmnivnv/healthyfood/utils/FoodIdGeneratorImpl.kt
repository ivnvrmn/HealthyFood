package com.rmnivnv.healthyfood.utils

class FoodIdGeneratorImpl : FoodIdGenerator {
    override suspend fun generateId(): Int {
        return (1..200).random()
    }
}