package com.rmnivnv.healthyfood.di

import com.rmnivnv.api.interceptor.AuthorizationInterceptor
import com.rmnivnv.api.repository.FoodRepository
import com.rmnivnv.api.LifesumApi
import com.rmnivnv.healthyfood.repository.FoodRepositoryImpl
import com.rmnivnv.healthyfood.utils.FoodIdGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.lifesum.com/"

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideLifesumApi(
        retrofit: Retrofit
    ): LifesumApi {
        return retrofit.create(LifesumApi::class.java)
    }

    @Provides
    fun provideFoodRepository(
        lifesumApi: LifesumApi,
        foodIdGenerator: FoodIdGenerator
    ): FoodRepository {
        return FoodRepositoryImpl(lifesumApi, foodIdGenerator)
    }
}