package com.rmnivnv.design.utils

import android.graphics.Color

internal object RandomColor {

    fun get() = Color.argb(255,
        getColorNumber(),
        getColorNumber(),
        getColorNumber()
    )

    private fun getColorNumber() = (1..256).random()
}