package com.ccu.kyapp.majorImage

import android.content.Context
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView

class PinchZoomImageView constructor(private val getContext: Context) :androidx.appcompat.widget.AppCompatImageView(getContext){
    private var scaleFactor : Float = 1.0f
    private var mScaleGesture:ScaleGestureDetector = ScaleGestureDetector(getContext, object:ScaleGestureDetector.OnScaleGestureListener{
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            if(detector != null){
                scaleFactor *= detector.scaleFactor
            }

        }
    })

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleGesture.onTouchEvent(event)
        return true
    }

}