package com.CCU.kyapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.prnd.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_major.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        YouTubePlayerView_major.play("UTx1igNpTpk")
    }
}
