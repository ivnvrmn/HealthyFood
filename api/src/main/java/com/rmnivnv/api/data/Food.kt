package com.rmnivnv.api.data

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("calories")
    val calories: Float?,
    @SerializedName("carbs")
    val carbs: Float?,
    @SerializedName("protein")
    val protein: Float?,
    @SerializedName("fat")
    val fat: Float?,
    @SerializedName("saturatedfat")
    val saturatedfat: Float?,
    @SerializedName("unsaturatedfat")
    val unsaturatedfat: Float?,
    @SerializedName("fiber")
    val fiber: Float?,
    @SerializedName("cholesterol")
    val cholesterol: Float?,
    @SerializedName("sugar")
    val sugar: Float?,
    @SerializedName("sodium")
    val sodium: Float?,
    @SerializedName("potassium")
    val potassium: Float?,
    @SerializedName("gramsperserving")
    val gramsperserving: Float?,
    @SerializedName("pcstext")
    val pcstext: String?
)