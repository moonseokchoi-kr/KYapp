package com.CCU.kyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {
    //private val mStorageRef: StorageReference? = FirebaseStorage.getInstance().reference;
    //var url = ""
    //private val mAuth = FirebaseAuth.getInstance()
    //private val user : FirebaseUser? = mAuth.currentUser
    private lateinit var imagePager:ImagePager
    private lateinit var viewPager:ViewPager

    class activeClickListener : View.OnClickListener {
        override fun onClick(v: View?) {

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent : Intent = Intent(this, LoadingActivity::class.java)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<ViewPager>(R.id.viewPager_journal)
        imagePager = ImagePager(this)
        viewPager.adapter = imagePager
        //YouTubePlayerView_major.play("UTx1igNpTpk")
        //RelativeLayout_pdf.setOnClickListener {intent.putExtra("url" , url)
            //startActivity(intent)}
    }
    /*
    public override fun onStart() {
        super.onStart()
        if(user != null){
           mStorageRef?.child("promote_pdf/prime_promote.pdf")?.downloadUrl
               ?.addOnSuccessListener { url = it.toString()
                   Log.d("PdfUri", "Uri : $url")
               }
               ?.addOnFailureListener{ Log.e("Error" , "URLDownload Failure:Firebase") }
        }else{
            signInAnonymously();
        }
    }
    private fun signInAnonymously() {
        mAuth.signInAnonymously()
            .addOnSuccessListener(this) {
                url =  mStorageRef?.child("promote_pdf/prime_promote.pdf")?.downloadUrl.toString()
            }
            .addOnFailureListener(
                this
            ) { exception -> Log.e("Login", "signInAnonymously:FAILURE", exception) }
    }
    */
}
