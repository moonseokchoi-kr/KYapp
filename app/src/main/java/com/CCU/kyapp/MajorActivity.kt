package com.CCU.kyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
