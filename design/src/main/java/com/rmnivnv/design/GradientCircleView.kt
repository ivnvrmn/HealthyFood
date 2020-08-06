package com.rmnivnv.design

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.rmnivnv.design.utils.RandomColor
import kotlin.math.cos
import kotlin.math.sin

class GradientCircleView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val margin = resources.getDimension(R.dimen.circle_margin)
    private val shadowSize = resources.getDimension(R.dimen.circle_shadow_size)

    private var startColor: Int = RandomColor.get()
    private var endColor: Int = RandomColor.get()

    private var angleNum = 45.0
    private val angle
        get() = Math.toRadians(angleNum)

    private val spinAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        duration = 300
        addUpdateListener {
            angleNum += 2
            invalidate()
        }
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, width)
    }

    override fun onDraw(canvas: Canvas) {
        val endX = cos(angle) * width
        val endY = sin(angle) * height
        paint.shader = LinearGradient(0f, 0f, endX.toFloat(), endY.toFloat(), intArrayOf(startColor, endColor), null, Shader.TileMode.MIRROR)
        paint.setShadowLayer(shadowSize, 0f, 10f, Color.BLACK)

        canvas.drawCircle(width / 2f, height / 2f, (width / 2f) - margin, paint)
    }

    fun reDraw() {
        startColor = RandomColor.get()
        endColor = RandomColor.get()
        invalidate()
    }

    fun startSpin() {
        spinAnimator.start()
    }

    fun stopSpin() {
        spinAnimator.cancel()
    }
}