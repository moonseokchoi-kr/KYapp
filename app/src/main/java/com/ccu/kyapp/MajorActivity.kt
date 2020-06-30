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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.majorImage.ImagePagerUri
import com.ccu.kyapp.majorTab.IntroTabViewAdapter
import com.ccu.kyapp.majorTab.TabItem
import com.ccu.kyapp.majorTab.TabViewTypes
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_major.*
import kotlinx.android.synthetic.main.admission_tab.*
import kotlinx.android.synthetic.main.intro_tab.*
import org.w3c.dom.Text
import java.lang.Runnable
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
    /* set Adapter*/
    private lateinit var adapter : IntroTabViewAdapter
    /* this map is matches string previous view*/
    private val majorMap : Map<String, String> = mapOf("software" to "기업소프트웨어학과",
        "clinical" to "임상의약학과", "extinguish" to "재난안전소방학과",
        "security" to "사이버보안공학과", "machine" to "융합기계공학과",
        "beauty" to "글로벌의료뷰티학과", "it" to "융합IT학과",
        "bio" to "의약바이오학과", "gfs" to "글로벌프론티어학과",
        "design" to "융합디자인학과")

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPager = ViewPager2(this).apply{
            parent
        }
        setContentView(R.layout.activity_major)
        /* set intent previous page*/
        val intent = intent
        Log.d("Admission url" , "${intent.getStringArrayListExtra("Admission")}")
        val tabItems : Array<TabItem> = arrayOf(
            TabItem("학과소개", TabViewTypes.INTRO, intent.getStringArrayListExtra("Urls")),
            TabItem("홍보영상", TabViewTypes.VIDEO, "UTx1igNpTpk"),
            TabItem("입시정보", TabViewTypes.ADMISSION, intent.getStringArrayListExtra("Admission"))
        )

        //Log.d("Count of Url", intent.getStringArrayListExtra("Urls").size.toString())
        /* set adapter for view page ImagePagerUri is adapter for ViewPager2*/
        adapter  = IntroTabViewAdapter(tabItems)
        viewPager2_tab.adapter = adapter
        viewPager2_tab.isUserInputEnabled = false

        TabLayoutMediator(tabLayout_major,viewPager2_tab){tab,position ->
            tab.text = tabItems[position].getTabName()
        }.attach()

        textView_toolbarText.text = majorMap[intent.getStringExtra("major")]
        /* using toolbar*/
        val tb = Toolbar_major
        setSupportActionBar(tb)
        val ab = supportActionBar
        /*add back button toolbar*/
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title = ""
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

}


