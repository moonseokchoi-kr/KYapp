package com.ccu.kyapp.majorImage

import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import kotlin.math.abs



/**
 * using pinch zoom function in image view
 */
class PinchZoomImageView @JvmOverloads constructor(context: Context, attrs : AttributeSet? = null, defStyle : Int =0) : androidx.appcompat.widget.AppCompatImageView(context,attrs,defStyle) {
    companion object{
        private const val NONE = 0;
        private const val DRAG = 1;
        private const val ZOOM = 2;
        private const val CLICK = 3
    }
    private var mode  = NONE
    var nMatrix = Matrix()
    var last = PointF()
    var start = PointF()
    var minScale = 1f
    var maxScale = 3f
    var m: FloatArray = FloatArray(9)
    var viewWidth = 0
    var viewHeight = 0
    var saveScale = 1f
    var origWidth = 0f
    var origHeight = 0f
    var oldMeasuredWidth = 0
    var oldMeasuredHeight = 0
    var mGestureDetector :GestureDetector
    private var mScaleDetector: ScaleGestureDetector
    init{
        imageMatrix = nMatrix
        scaleType = ScaleType.MATRIX
        mScaleDetector = ScaleGestureDetector(context, object:ScaleGestureDetector.SimpleOnScaleGestureListener(){
             override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                 mode = ZOOM
                 return true
             }

             override fun onScale(detector: ScaleGestureDetector?): Boolean {
                if(detector != null) {
                    var mScaleFactor = detector.scaleFactor
                    val origScale = saveScale
                    saveScale *= mScaleFactor
                    if (saveScale > maxScale) {
                        saveScale = maxScale
                        mScaleFactor = maxScale / origScale
                    } else if (saveScale < minScale) {
                        saveScale = minScale
                        mScaleFactor = minScale / origScale
                    }
                    if (origWidth * saveScale <= viewWidth || origHeight * saveScale <= viewHeight) nMatrix.postScale(
                        mScaleFactor,
                        mScaleFactor,
                        (viewWidth / 2).toFloat(),
                        (viewHeight / 2).toFloat()
                    ) else nMatrix.postScale(
                        mScaleFactor,
                        mScaleFactor,
                        detector.focusX,
                        detector.focusY
                    )
                    fixTrans()
                    return true
                }
                return false
            }
         })
        mGestureDetector = GestureDetector(context,object: GestureDetector.SimpleOnGestureListener(){
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                val origScale = saveScale
                val mScaleFactor: Float

                if(saveScale == maxScale){
                    saveScale = minScale
                    mScaleFactor = minScale/origScale
                }else{
                    saveScale = maxScale
                    mScaleFactor = maxScale/origScale
                }
                nMatrix.postScale(mScaleFactor, mScaleFactor, viewWidth/2.toFloat(), viewHeight/2.toFloat())
                fixTrans()
                return true
            }
        })

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(event)
        mScaleDetector.onTouchEvent(event)
        if(event != null){
            val curr = PointF(event.x, event.y)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    last.set(curr)
                    start.set(last)
                    mode = DRAG
                }
                MotionEvent.ACTION_MOVE -> if (mode == DRAG) {
                    val deltaX = curr.x - last.x
                    val deltaY = curr.y - last.y
                    val fixTransX: Float =
                        getFixDragTrans(deltaX, viewWidth.toFloat(), origWidth * saveScale)
                    val fixTransY: Float =
                        getFixDragTrans(deltaY, viewHeight.toFloat(), origHeight * saveScale)
                    nMatrix.postTranslate(fixTransX, fixTransY)
                    fixTrans()
                    last[curr.x] = curr.y
                }
                MotionEvent.ACTION_UP -> {
                    mode = NONE
                    val xDiff = abs(curr.x - start.x).toInt()
                    val yDiff = abs(curr.y - start.y).toInt()
                    if (xDiff < CLICK && yDiff < CLICK) performClick()
                }
                MotionEvent.ACTION_POINTER_UP -> mode = NONE
            }
            imageMatrix = nMatrix
            invalidate()
            return true
        }
        return false
    }
    fun fixTrans() {
        nMatrix.getValues(m)
        val transX = m[Matrix.MTRANS_X]
        val transY = m[Matrix.MTRANS_Y]
        val fixTransX: Float = getFixTrans(transX, viewWidth.toFloat(), origWidth * saveScale)
        val fixTransY: Float = getFixTrans(transY, viewHeight.toFloat(), origHeight * saveScale)
        if (fixTransX != 0f || fixTransY != 0f) nMatrix.postTranslate(fixTransX, fixTransY)
    }
    private fun getFixTrans(
        trans: Float,
        viewSize: Float,
        contentSize: Float
    ): Float {
        val minTrans: Float
        val maxTrans: Float
        if (contentSize <= viewSize) {
            minTrans = 0f
            maxTrans = viewSize - contentSize
        } else {
            minTrans = viewSize - contentSize
            maxTrans = 0f
        }
        if (trans < minTrans) return -trans + minTrans
        return if (trans > maxTrans) -trans + maxTrans else 0f
    }

    private fun getFixDragTrans(
        delta: Float,
        viewSize: Float,
        contentSize: Float
    ): Float {
        return if (contentSize <= viewSize) {
            0f
        } else delta
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        //
        // Rescales image on rotation
        //
        //
        // Rescales image on rotation
        //
        if (oldMeasuredHeight == viewWidth && oldMeasuredHeight == viewHeight || viewWidth == 0 || viewHeight == 0
        ) return
        oldMeasuredHeight = viewHeight
        oldMeasuredWidth = viewWidth
        if (saveScale == 1f) {
            //Fit to screen.
            val scale: Float
            val drawable = drawable
            if (drawable == null || drawable.intrinsicWidth == 0 || drawable.intrinsicHeight == 0) return
            val bmWidth = drawable.intrinsicWidth
            val bmHeight = drawable.intrinsicHeight
            Log.d("bmSize", "bmWidth: $bmWidth bmHeight : $bmHeight")
            val scaleX = viewWidth.toFloat() / bmWidth.toFloat()
            val scaleY = viewHeight.toFloat() / bmHeight.toFloat()
            scale = scaleX.coerceAtMost(scaleY)
            nMatrix.setScale(scale, scale)
            // Center the image
            var redundantYSpace =
                viewHeight.toFloat() - scale * bmHeight.toFloat()
            var redundantXSpace =
                viewWidth.toFloat() - scale * bmWidth.toFloat()
            redundantYSpace /= 2.toFloat()
            redundantXSpace /= 2.toFloat()
            nMatrix.postTranslate(redundantXSpace, redundantYSpace)
            origWidth = viewWidth - 2 * redundantXSpace
            origHeight = viewHeight - 2 * redundantYSpace
            imageMatrix = nMatrix
        }
        fixTrans()
    }

}