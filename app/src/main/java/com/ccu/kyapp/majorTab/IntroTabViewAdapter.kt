package com.ccu.kyapp.majorTab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ccu.kyapp.R
import com.ccu.kyapp.majorImage.ImagePagerUri
import kr.co.prnd.YouTubePlayerView

/**
 * this class Adapter for ViewPager of activity_major.xml
 * set content of ViewPager
 *
 * @author MoonSeok Choi
 * @version 0.1 create function and set variable
 * @version 0.2 set variable in ViewHolder class
 * @since 2020.06.30
 */
class IntroTabViewAdapter(private val tabItems: Array<TabItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var rootView : View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         when (viewType) {
            TabViewTypes.INTRO -> {
                rootView =
                    LayoutInflater.from(parent.context).inflate(R.layout.intro_tab, parent, false)
                return IntroViewHolder(rootView)
            }
            TabViewTypes.VIDEO -> {
                rootView =
                    LayoutInflater.from(parent.context).inflate(R.layout.video_tab, parent, false)
                return VideoViewHolder(rootView)
            }

            else -> {
                rootView =
                    LayoutInflater.from(parent.context).inflate(R.layout.admission_tab, parent, false)
                return AdmissionViewHolder(rootView)
            }
        }
    }

    override fun getItemCount(): Int {
        return tabItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is IntroViewHolder-> holder.bind(position, tabItems)
            is VideoViewHolder-> holder.bind(position, tabItems)
            is AdmissionViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return tabItems[position].getViewType()
    }

}

/**
 * Intro tab view holder
 * @author MoonSeok Choi
 * @version 0.1 create class
 * @version 0.2 set imgSlider and create bind
 * @since 2020.06.30
 */
class IntroViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    private val imgSlider : ViewPager2 = itemView.findViewById(R.id.viewPager2_major)
    fun bind (position: Int, tabItems: Array<TabItem>){
        imgSlider.adapter = ImagePagerUri(tabItems[position].getContent()as List<String>)
    }
}

/**
 * Video tab view holder
 * @author MoonSeok Choi
 * @version 0.1 create class
 * @version 0.2 set video and create bind
 * @since 2020.06.30
 */
class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val video : YouTubePlayerView = itemView.findViewById(R.id.YouTubePlayerView_major)
    fun bind(position: Int, tabItems: Array<TabItem>){
        video.play(tabItems[position].getContent() as String)
    }
}

/**
 * admission tab view holder
 * @author MoonSeok Choi
 * @version 0.1 create class
 * @since 2020.06.30
 */
class AdmissionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(){}

}