package com.ccu.kyapp.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ccu.kyapp.R

class NewsAdapter(var newsItems : Array<NewItem>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_slider, parent, false))
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder){
            this.bindImage(newsItems[position].imgUrl)
            title.text = newsItems[position].title
            desc.text = newsItems[position].description
        }
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgView : ImageView = itemView.findViewById(R.id.imageView_card)
    val title : TextView = itemView.findViewById(R.id.textView_cardTitle)
    val desc : TextView = itemView.findViewById(R.id.textView_desc)
    fun bindImage (src : String){
        Glide.with(itemView).load(src).into(imgView)
    }
}