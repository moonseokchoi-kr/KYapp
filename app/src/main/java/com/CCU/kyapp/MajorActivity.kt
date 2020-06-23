package com.CCU.kyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_major.*

class MajorActivity : AppCompatActivity() {
    private lateinit var imagePager:ImagePager
    private lateinit var viewPager: ViewPager
    private val imgList = intArrayOf(R.drawable.slide_i,R.drawable.slide_ii,R.drawable.slide_iii,R.drawable.slide_iiii,R.drawable.slide_iiiii,R.drawable.slide_iiiiii,R.drawable.slide_iiiiiii,R.drawable.slide_iiiiiiii,R.drawable.slide_iiiiiiiii
    ,R.drawable.slide_iiiiiiiiii,R.drawable.slide_iiiiiiiiiii, R.drawable.slide_iiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiiiiiii
    ,R.drawable.slide_iiiiiiiiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiiiiiiiii, R.drawable.slide_iiiiiiiiiiiiiiiiiiii)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        imagePager = ImagePager(this,imgList)
        viewPager = viewPager_major
        viewPager.adapter = imagePager
        var tb = Toolbar_major
        setSupportActionBar(tb)
        var ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""
        textView_majorTitle.text = intent.getStringExtra("major")
        YouTubePlayerView_major.play("vtx3-q8IBMs")
    }
}
