package com.ccu.kyapp.majorImage

import android.content.Context
import android.graphics.PointF
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.MotionEvent.INVALID_POINTER_ID
import android.view.ScaleGestureDetector
import androidx.core.view.MotionEventCompat
import com.google.android.youtube.player.internal.m
import kotlin.math.max
import kotlin.math.min


/**
 * using pinch zoom function in image view
 */
class PinchZoomImageView @JvmOverloads constructor(context: Context, attrs : AttributeSet? = null, defStyle : Int =0) : androidx.appcompat.widget.AppCompatImageView(context,attrs,defStyle) {
    companion object{
        private const val NONE = 0;
        private const val DRAG = 1;
        private const val ZOOM = 2;
    }
    private val mContentRect : Rect? = null
    private var mode  = NONE
    private var scaleFactor : Float = 1.0f
    private var mScaleGesture:ScaleGestureDetector
    private var mGestureDetector : GestureDetector
    init{
         mScaleGesture = ScaleGestureDetector(context, object:ScaleGestureDetector.SimpleOnScaleGestureListener(){
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                if(detector != null) {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = max(1.0f,min(scaleFactor,10.0f))

                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    return true
                }
                return false
            }
         })
        mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener(){
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {

            }
        })
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleGesture.onTouchEvent(event)
        return true
    }

}