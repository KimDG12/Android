package com.icarus.recycle_app

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import java.lang.Float.min

class ZoomAndDragImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val matrix = Matrix()
    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private var secondTouchX: Float = 0f
    private var secondTouchY: Float = 0f

    private val scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor
            matrix.postScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
            imageMatrix = matrix
            return true
        }
    })

    init {
        scaleType = ScaleType.MATRIX
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setCenteredScaledImage()
    }

    fun setCenteredScaledImage() {
        matrix.reset()
        val drawable = drawable ?: return

        val imgWidth = drawable.intrinsicWidth.toFloat()
        val imgHeight = drawable.intrinsicHeight.toFloat()

        val viewWidth = width.toFloat()
        val viewHeight = height.toFloat()

        val scale: Float = min(viewWidth / imgWidth, viewHeight / imgHeight)

        matrix.postScale(scale, scale)
        matrix.postTranslate((viewWidth - imgWidth * scale) / 2f, (viewHeight - imgHeight * scale) / 2f)

        imageMatrix = matrix
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = event.x
                lastTouchY = event.y
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                // 두 번째 터치가 시작될 때 위치를 저장
                secondTouchX = event.getX(1)
                secondTouchY = event.getY(1)
            }
            MotionEvent.ACTION_MOVE -> {
                // 확대/축소 중이 아닐 때만 드래그 수행
                if (!scaleGestureDetector.isInProgress) {
                    val dx = event.x - lastTouchX
                    val dy = event.y - lastTouchY
                    matrix.postTranslate(dx, dy)
                    imageMatrix = matrix
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                performClick()
            }
        }

        lastTouchX = event.x
        lastTouchY = event.y

        return true
    }



    override fun performClick(): Boolean {
        return super.performClick()
    }
}

