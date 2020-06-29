package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ccu.kyapp.majorImage.ImagePager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * this script is active for main activity
 * Define the behavior when you click the layout.
 *
 * @author MoonSeok Choi
 * @version 1.0
 * @since 2020.6.25
 */

class MainActivity : AppCompatActivity() {
    /*
    set ImagePager
     */
    private lateinit var imagePager: ImagePager
    /*
    set ViewPager
     */
    private lateinit var viewPager:ViewPager
    //test image
    private val imgList = intArrayOf(
        R.drawable.izone_main,
        R.drawable.izone_member,
        R.drawable.gfriend_main
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        intent is set next page
         */
        lateinit var intent : Intent
        setContentView(R.layout.activity_main)
        /*
        viewPager is used to image slide
         */
        viewPager = findViewById(R.id.viewPager_journal)
        viewPager.adapter = ImagePager(this, imgList)

        //move to major_selector
        relativeLayout_major.setOnClickListener{
            intent = Intent(this, MajorSelectorActivity::class.java)
            startActivity(intent)
        }
        //move to school introduce
        relativeLayout_school.setOnClickListener {
            intent  = Intent(this, SchoolActivity::class.java)
            startActivity(intent)
        }
        //move to admission page
        relativeLayout_admission.setOnClickListener{
            intent = Intent(this, AdmissionActivity::class.java)
            startActivity(intent)
        }


    }

}
