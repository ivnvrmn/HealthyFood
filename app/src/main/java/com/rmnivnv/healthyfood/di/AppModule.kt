package com.rmnivnv.healthyfood.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rmnivnv.healthyfood.utils.FoodIdGenerator
import com.rmnivnv.healthyfood.utils.FoodIdGeneratorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideIdGenerator(): FoodIdGenerator {
        return FoodIdGeneratorImpl()
    }
}