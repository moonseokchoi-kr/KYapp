package com.CCU.kyapp

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_major.*
import kotlinx.android.synthetic.main.activity_pdfview.*


class ImagePager constructor(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val imgList = intArrayOf(R.drawable.izone_main, R.drawable.izone_member, R.drawable.gfriend_main)
    lateinit var inflater : LayoutInflater
    var context:Context = context

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == (`object`as ImageView)
    }

    override fun getCount(): Int {
        return imgList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view : View = inflater.inflate(R.layout.activity_main, container, false)
        var imageView : ImageView = view.findViewById(R.id.imageView_journal)
        imageView.setImageResource(imgList[position])
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
        container.invalidate()
    }


}
