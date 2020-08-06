package com.rmnivnv.design

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView

class BlackButtonView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleView: AppCompatTextView
    var title: String? = null
        set(value) {
            titleView.text = value
            field = value
        }

    init {
        View.inflate(context, R.layout.black_button_view, this)

        titleView = findViewById(R.id.title)

        background = context.getDrawable(R.drawable.background_selector)

        isClickable = true
        isFocusable = true
    }
}