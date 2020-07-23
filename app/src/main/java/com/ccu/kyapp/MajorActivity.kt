

package com.ccu.kyapp

import android.animation.LayoutTransition
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.majorImage.ImagePagerViewHolder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_major.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * class : MajorActivity
 *
 * This script is responsible for the behavior of the views that introduce each subject.
 *
 * @author MoonSeok Choi
 * @version 0.1 set major view and click event
 * @version 0.2 change the major view using ViewPager and TabLayout
 * @version 0.3 videoplayer connect to data base
 * @see None
 * @since 2020.06.26
 *
 */
class MajorActivity : AppCompatActivity() {
    /* this map is matches string previous view*/
    private val majorMap : Map<String?, Any> = mapOf("software" to arrayOf("기업소프트웨어학과","http://www.konyang.ac.kr/software.do"),
        "clinical" to arrayOf("임상의약학과","http://www.konyang.ac.kr/clinicalmed.do"), "extinguish" to arrayOf("재난안전소방학과","http://www.konyang.ac.kr/fire.do"),
        "security" to arrayOf("사이버보안공학과","http://www.konyang.ac.kr/sec.do"), "machine" to arrayOf("융합기계공학과","http://www.konyang.ac.kr/mce.do"),
        "beauty" to arrayOf("글로벌의료뷰티학과","http://www.konyang.ac.kr/kbeauty.do"), "it" to arrayOf("융합IT학과","http://www.konyang.ac.kr/computer.do"),
        "bio" to arrayOf("의약바이오학과","http://www.konyang.ac.kr/medbiosci.do"), "gfs" to arrayOf("글로벌프론티어학과","http://gfs.konyang.ac.kr/"),
        "design" to arrayOf("융합디자인학과","https://www.ky-design.net/"))
    /*
     transit layout
     */
    private val transition : LayoutTransition = LayoutTransition()
    private var isOpen = false
    private var major : String? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        /* set intent previous page*/
        var intent = intent

        major = intent.getStringExtra("major")
        /* set adapter for view page ImagePagerUri is adapter for ViewPager2*/
        textView_toolbarText.text = (majorMap[major] as Array<String>)[0]
        /* using toolbar*/
        val tb = Toolbar_major
        setSupportActionBar(tb)
        val ab = supportActionBar
        /*add back button toolbar*/
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowTitleEnabled(false)
        lifecycle.addObserver(YouTubePlayerView_major)
        if(intent.getStringExtra("videoID") != null){
            recyclerView_video.visibility = View.GONE
            YouTubePlayerView_major.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(intent.getStringExtra("videoID"),0f)
                }
            })
        }
        else if (intent.getStringArrayListExtra("videoIDs")!=null){
            YouTubePlayerView_major.visibility = View.GONE
            recyclerView_video.adapter = VideoViewAdapter(intent.getStringArrayListExtra("videoIDs"))
            Log.d("VideoIDs",intent.getStringArrayListExtra("videoIDs").toString())
        }

        relativeLayout_intro.setOnClickListener{
            Log.d("click intro ", "Click!!")
            intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("major",major)
            intent.putExtra("select", "major")
            startActivity(intent)
        }
        relativeLayout_admission.setOnClickListener{
                intent = Intent(this, LoadingActivity::class.java)
                intent.putExtra("major",major)
                intent.putExtra("select","admission")
                startActivity(intent)
        }

        relativeLayout_video.setOnClickListener {
            transition.addChild(linearLayout_video, linearLayout_video)

            // open
            if(!isOpen) {
                linearLayout_video.visibility= View.VISIBLE
                isOpen = true
                enableLayoutTransitions()
            }
            //close
            else{
                enableLayoutTransitions()
                linearLayout_video.visibility= View.GONE
                isOpen = false
            }

        }

        relativeLayout_homePage.setOnClickListener{
            val goWeb = Intent(Intent.ACTION_VIEW, Uri.parse((majorMap[major] as Array<String>)[1]))
            startActivity(goWeb)
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
/**
 * This class is Adapter for recycler view that this play video
 *
 * @author MoonSeok Choi
 * @version 0.1 make class and create function
 * @see None
 * @since 2020.07,23
 */
private class VideoViewAdapter(var videoIDs : ArrayList<String>) : RecyclerView.Adapter<VideoHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_tab,parent,false)
        return VideoHolder(view)
    }

    override fun getItemCount(): Int {
        return videoIDs.size
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videoIDs[position])
    }

}
private class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var videoPlayer: YouTubePlayerView = itemView.findViewById(R.id.YouTubePlayerView_major)
    fun bind(videoID : String){
        videoPlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoID,0f)
            }
        })
    }
}


