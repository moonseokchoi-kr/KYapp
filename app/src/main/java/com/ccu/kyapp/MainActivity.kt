package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ccu.kyapp.majorImage.ImagePager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var imagePager: ImagePager
    private lateinit var viewPager:ViewPager
    //test image
    private val imgList = intArrayOf(
        R.drawable.izone_main,
        R.drawable.izone_member,
        R.drawable.gfriend_main
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var intent : Intent
        setContentView(R.layout.activity_main)
        viewPager = findViewById<ViewPager>(R.id.viewPager_journal)

        imagePager = ImagePager(this, imgList)
        viewPager.adapter = imagePager
        relativeLayout_major.setOnClickListener{
            intent = Intent(this, MajorSelectorActivity::class.java)
            startActivity(intent)
        }
        relativeLayout_school.setOnClickListener {
            intent  = Intent(this, SchoolActivity::class.java)
            startActivity(intent)
        }
        relativeLayout_admission.setOnClickListener{
            intent = Intent(this, AdmissionActivity::class.java)
            startActivity(intent)
        }


    }

}
