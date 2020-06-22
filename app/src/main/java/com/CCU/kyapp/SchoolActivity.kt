package com.CCU.kyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_major.*

class SchoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)
        YouTubePlayerView_major.play("UTx1igNpTpk")
        //RelativeLayout_pdf.setOnClickListener {intent.putExtra("url" , url)
        //startActivity(intent)}
    }
}
