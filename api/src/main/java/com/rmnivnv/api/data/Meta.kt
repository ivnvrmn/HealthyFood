package com.rmnivnv.api.data

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    val code: Int,
    @SerializedName("errorType")
    val errorType: Type,
    @SerializedName("errorDetail")
    val errorDetail: String
) {
    enum class Type {
        @SerializedName("unknown_error")
        UNKNOWN_ERROR
    }
}