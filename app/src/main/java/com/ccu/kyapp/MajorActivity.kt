package com.ccu.kyapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.majorImage.ImagePager
import kotlinx.android.synthetic.main.activity_major.*

class MajorActivity : AppCompatActivity() {
    private lateinit var imagePager: ImagePager
    private lateinit var viewPager: ViewPager
    private val imgList = intArrayOf(
        R.drawable.slide_i,
        R.drawable.slide_ii,
        R.drawable.slide_iii,
        R.drawable.slide_iiii,
        R.drawable.slide_iiiii,
        R.drawable.slide_iiiiii,
        R.drawable.slide_iiiiiii,
        R.drawable.slide_iiiiiiii,
        R.drawable.slide_iiiiiiiii
    ,
        R.drawable.slide_iiiiiiiiii,
        R.drawable.slide_iiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiiiiiii
    ,
        R.drawable.slide_iiiiiiiiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiiiiiiiii,
        R.drawable.slide_iiiiiiiiiiiiiiiiiiii
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        imagePager = ImagePager(this, imgList)
        viewPager = viewPager_major
        var intent = intent
        viewPager.adapter = imagePager
        Log.d("major", intent.getStringExtra("major").toString())
        textView_toolbarText.text = intent.getStringExtra("major")
        textView_majorTitle.text = intent.getStringExtra("major")
        var tb = Toolbar_major
        setSupportActionBar(tb)
        var ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""
        YouTubePlayerView_major.play("vtx3-q8IBMs")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("BackButton", "Item Id " + item.itemId)
        Log.d("Home", "id :"+ R.id.home)
        when(item.itemId){
            android.R.id.home -> {finish()
                return true}
            else ->{
                Log.e("BackButton","Cant find ID")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
