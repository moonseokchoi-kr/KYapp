package com.ccu.kyapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.majorImage.ImagePagerUri
import kotlinx.android.synthetic.main.activity_major.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MajorActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var auth : FireBaseAuth
    private val scope = CoroutineScope(Dispatchers.IO)
    private val majorMap : Map<String, String> = mapOf("software" to "기업소프트웨어학과",
        "clinical" to "임상의약학과", "extinguish" to "재난안전소방학과",
        "security" to "사이버보안공학과", "machine" to "융합기계공학과",
        "beauty" to "글로벌의료뷰티학과", "it" to "융합IT학과",
        "bio" to "의약바이오학과", "gfs" to "글로벌프론티어학과",
        "design" to "융합디자인학과")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        viewPager = viewPager_major
        var intent = intent
        auth = FireBaseAuth(intent.getStringExtra("major"),this)
        Log.d("Count of Url", intent.getStringArrayListExtra("Urls").size.toString())
        viewPager.adapter = ImagePagerUri(intent.getStringArrayListExtra("Urls"))
        viewPager_major.setOnClickListener()
        textView_toolbarText.text = majorMap[intent.getStringExtra("major")]
        textView_majorTitle.text = majorMap[intent.getStringExtra("major")]
        var tb = Toolbar_major
        setSupportActionBar(tb)
        var ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""
        YouTubePlayerView_major.play("vtx3-q8IBMs")
        Log.d("major", intent.getStringExtra("major").toString())

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
