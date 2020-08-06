package com.rmnivnv.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_AUTHORIZATION = "Authorization"
private const val AUTHORIZATION_TOKEN = "23863708:465c0554fd00da006338c72e282e939fe6576a25fd00c776c0fbe898c47c9876"

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(
                HEADER_AUTHORIZATION,
                AUTHORIZATION_TOKEN
            )
            .build()
        return chain.proceed(request)
    }
}