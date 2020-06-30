package com.ccu.kyapp.majorTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ccu.kyapp.R

/**
 * this class Adapter for ViewPager of activity_major.xml
 * set content of ViewPager
 *
 * @author MoonSeok Choi
 * @version 0.1 create function and set variable
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

    }

}

/**
 * Intro tab view holder
 * @author MoonSeok Choi
 * @version 0.1 create class
 * @since 2020.06.30
 */
class IntroViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

}

/**
 * Video tab view holder
 * @author MoonSeok Choi
 * @version 0.1 create class
 * @since 2020.06.30
 */
class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

}

/**
 * admission tab view holder
 * @author MoonSeok Choi
 * @version 0.1 create class
 * @since 2020.06.30
 */
class AdmissionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

}