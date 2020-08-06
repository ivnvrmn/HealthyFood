package com.rmnivnv.design

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView

class InfoView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleView: AppCompatTextView
    private val descriptionView: AppCompatTextView

    var title: String? = null
        set(value) {
            titleView.text = value
            field = value
        }
    var description: String? = null
        set(value) {
            descriptionView.text = value
            field = value
        }

    init {
        View.inflate(context, R.layout.info_view, this)
        titleView = findViewById(R.id.infoTitle)
        descriptionView = findViewById(R.id.infoDescription)
    }
}