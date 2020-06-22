package com.CCU.kyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter


class ImagePager constructor(var context: Context): PagerAdapter() {
    private val imgList = intArrayOf(R.drawable.izone_main, R.drawable.izone_member, R.drawable.gfriend_main)
    private lateinit var inflater : LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imgList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view : View = inflater.inflate(R.layout.image_slider, container, false)
        var imageView : ImageView = view.findViewById(R.id.imageView_slider)
        imageView.setImageResource(imgList[position])
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
        container.invalidate()
    }


}
