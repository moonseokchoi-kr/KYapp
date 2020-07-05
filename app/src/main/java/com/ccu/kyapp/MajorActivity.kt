

package com.ccu.kyapp

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_major.*

/**
 * class : MajorActivity
 *
 * This script is responsible for the behavior of the views that introduce each subject.
 *
 * @author MoonSeok Choi
 * @version 0.1 set major view and click event
 * @version 0.2 change the major view using ViewPager and TabLayout
 * @see None
 * @since 2020.06.26
 *
 */
class MajorActivity : AppCompatActivity() {
    /* this map is matches string previous view*/
    private val majorMap : Map<String, String> = mapOf("software" to "기업소프트웨어학과",
        "clinical" to "임상의약학과", "extinguish" to "재난안전소방학과",
        "security" to "사이버보안공학과", "machine" to "융합기계공학과",
        "beauty" to "글로벌의료뷰티학과", "it" to "융합IT학과",
        "bio" to "의약바이오학과", "gfs" to "글로벌프론티어학과",
        "design" to "융합디자인학과")
    /*
     transit layout
     */
    private val transition : LayoutTransition = LayoutTransition()
    private var isOpen = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPager = ViewPager2(this).apply{
            parent
        }
        setContentView(R.layout.activity_major)
        /* set intent previous page*/
        var intent = intent
        Log.d("Admission url" , "${intent.getStringArrayListExtra("Admission")}")
        val major  = intent.getStringExtra("major")
        //Log.d("Count of Url", intent.getStringArrayListExtra("Urls").size.toString())
        /* set adapter for view page ImagePagerUri is adapter for ViewPager2*/
        YouTubePlayerView_major.play("EMFqPjAs5Ak")
        textView_toolbarText.text = majorMap[major]
        /* using toolbar*/
        val tb = Toolbar_major
        setSupportActionBar(tb)
        val ab = supportActionBar
        /*add back button toolbar*/
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowTitleEnabled(false)

        relativeLayout_intro.setOnClickListener{
            Log.d("click intro ", "Click!!")
            intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("major",major)
            intent.putExtra("select", "major")
            startActivity(intent)
        }
        if(intent.getStringArrayListExtra("Admission") != null){
            relativeLayout_admission.setOnClickListener{
                intent = Intent(this, LoadingActivity::class.java)
                intent.putExtra("major",major)
                intent.putExtra("select","admission")
                startActivity(intent)
            }
        }
        relativeLayout_video.setOnClickListener {
            transition.addChild(scrollView_video, linearLayout_video)

            // open
            if(!isOpen) {
                scrollView_video.visibility = View.VISIBLE
                linearLayout_video.visibility= View.VISIBLE
                isOpen = true
                enableLayoutTransitions()
            }
            //close
            else{
                enableLayoutTransitions()
                scrollView_video.visibility= View.GONE
                linearLayout_video.visibility= View.GONE
                isOpen = false
            }

        }
    }

    /**
     * when touch the back button on toolbar move to previous page
     *
     * @param MenuItem item item of toolbar (like back button)
     * @return boolean when find item return onOptionItemSelected if not return false
     */
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

    /**
     * set animation when layout change
     *
     * @param None
     * @return None
     */
    private fun enableLayoutTransitions(){
        transition.enableTransitionType(LayoutTransition.APPEARING)
        transition.enableTransitionType(LayoutTransition.DISAPPEARING)
        transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING)
        transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
    }

}


