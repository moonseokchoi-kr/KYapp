package com.CCU.kyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var imagePager:ImagePager
    private lateinit var viewPager:ViewPager

    class activeClickListener : View.OnClickListener {
        override fun onClick(v: View?) {

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent : Intent = Intent(this, MajorSelectorActivity::class.java)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<ViewPager>(R.id.viewPager_journal)
        imagePager = ImagePager(this)
        viewPager.adapter = imagePager
        relativeLayout_major.setOnClickListener{
            startActivity(intent)
        }
        relativeLayout_school.setOnClickListener {
            intent  = Intent(this, SchoolActivity::class.java)
            startActivity(intent)
        }


    }

}
