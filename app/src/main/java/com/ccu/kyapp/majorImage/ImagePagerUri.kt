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

class ImagePagerUri( var imgPathList: List<String>) : RecyclerView.Adapter<ImagePagerViewHolder>(){

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

class ImagePagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val imgView : ImageView = itemView.findViewById(R.id.imageView_slider)
    fun bind (imgPath: String){
        Log.d("imgView", "Load: $imgPath")
        Glide.with(itemView).load(imgPath).into(imgView)
    }
}