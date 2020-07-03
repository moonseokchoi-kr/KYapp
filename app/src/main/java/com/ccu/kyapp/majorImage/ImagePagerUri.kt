package com.ccu.kyapp.majorImage

import android.content.Context
import android.net.Uri
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ccu.kyapp.R

/**
 * Adapter for ViewPager2 it will make image slide
 *
 * @author MoonSeok Choi
 * @version 0.1 create adapter and write override function
 * @since 2020.06.30
 */
class ImagePagerUri(private val imgPathList: List<String>) : RecyclerView.Adapter<ImagePagerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_slider,parent,false)
        return ImagePagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imgPathList.size
    }

    override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
        holder.bind(imgPathList[position])
    }
}

/**
 * View Holder for Adapter
 * bind view attribute
 *
 * @author MoonSeok Choi
 * @version 0.1 create class and write bind function
 * @since 2020.06.30
 */
class ImagePagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val imgView : ImageView = itemView.findViewById(R.id.imageView_slider)
    fun bind (imgPath: String){
        Log.d("imgView", "Load: $imgPath")
        Glide.with(itemView).load(imgPath).into(imgView)
    }
}