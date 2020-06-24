package com.ccu.kyapp

import android.view.animation.TranslateAnimation

class BottomTranslateAnimation constructor(var fromY : Float, var toY: Int) : TranslateAnimation(0F,0F,fromY,toY as Float){

    override fun setDuration(duration: Long){
        super.setDuration(duration)
    }

}