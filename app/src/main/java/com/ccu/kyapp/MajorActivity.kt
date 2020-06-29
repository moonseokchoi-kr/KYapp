//todo write comment

package com.ccu.kyapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.majorImage.ImagePagerUri
import kotlinx.android.synthetic.main.activity_major.*
import java.lang.Runnable
/**
 * class : MajorActivity
 *
 * This script is responsible for the behavior of the views that introduce each subject.
 *
 * @author MoonSeok Choi
 * @version 1.0
 * @see None
 * @since 2020.06.26
 *
 */
class MajorActivity : AppCompatActivity() {
    /** set ViewPager 2*/
    private lateinit var viewPager: ViewPager2
    /** this map is matches string previous view*/
    private val majorMap : Map<String, String> = mapOf("software" to "기업소프트웨어학과",
        "clinical" to "임상의약학과", "extinguish" to "재난안전소방학과",
        "security" to "사이버보안공학과", "machine" to "융합기계공학과",
        "beauty" to "글로벌의료뷰티학과", "it" to "융합IT학과",
        "bio" to "의약바이오학과", "gfs" to "글로벌프론티어학과",
        "design" to "융합디자인학과")

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        viewPager = viewPager_major
        /* set intent previous page*/
        val intent = intent
        //Log.d("Count of Url", intent.getStringArrayListExtra("Urls").size.toString())
        /* set adapter for view page ImagePagerUri is adapter for ViewPager2*/
        viewPager.adapter = ImagePagerUri(intent.getStringArrayListExtra("Urls"))

        /* ScrollView */
        val scrollView  = ScrollView_intro

        textView_toolbarText.text = majorMap[intent.getStringExtra("major")]
        /* using toolbar*/
        val tb = Toolbar_major
        setSupportActionBar(tb)
        val ab = supportActionBar
        /*add back button toolbar*/
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""

        YouTubePlayerView_major.play("vtx3-q8IBMs")
        //Log.d("major", intent.getStringExtra("major").toString())

        textView_intro.setOnClickListener{
            focusOnView(scrollView, textView_intro,scrollView.findViewWithTag("textView_intro"))
            textView_video.setTextColor(Color.parseColor("#000000"))
        }
        textView_video.setOnClickListener{
            focusOnView(scrollView, textView_video,scrollView.findViewWithTag("textView_video"))
            textView_intro.setTextColor(Color.parseColor("#000000"))

        }
        textView_ad.setOnClickListener{
            //focusOnView(scrollView, textView_ad)
        }

    }
    /**
     * when touch the back button on toolbar move to previous page
     *
     * @param MenuItem item item of toolbar (like back button)
     * @return boolean when find item return onOpetionItemSelected if not return false
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
     * when click the text of top, scroll move to text on main layout
     *
     * @param ScrollView scrollView  scrollView layout
     * @param TextView tab text of view on Top
     * @param TextView view text of scrollView in main layout
     * @return None
     */
    private fun focusOnView(scrollView: ScrollView, tab : TextView,view : TextView){
            val runnable = object: Runnable {
                override fun run() {
                    scrollView.smoothScrollTo(0, view.top)
                    tab.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        Handler().post(runnable)
    }



}
