package com.CCU.kyapp

import android.view.View
import android.view.animation.TranslateAnimation

class BottomTranslateAnimation constructor(var fromY : Float, var toY: Float){

    var anim : TranslateAnimation = TranslateAnimation(0F, 0F,fromY,toY)

    fun setDuration(duration: Long){
        anim.duration = duration
    }

}