package com.CCU.kyapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.prnd.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_major.*

class MajorActivity : AppCompatActivity() {


    var url = "https://youtu.be/UTx1igNpTpk"
    val youtube_key = "AIzaSyDrI2s15niXWy3bZlE_Ms16h9oxG1oJt3E"
    val REQ_START_STANDALONE_PLAYER = 101
    val REQ_RESOLVE_SERVICE_MISSING = 2
    val RECOVERY_DIALOG_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
    }
}
