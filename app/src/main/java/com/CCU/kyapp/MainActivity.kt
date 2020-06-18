package com.CCU.kyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {
    //private val mStorageRef: StorageReference? = FirebaseStorage.getInstance().reference;
    //var url = ""
    //private val mAuth = FirebaseAuth.getInstance()
    //private val user : FirebaseUser? = mAuth.currentUser
    lateinit var imagePager:ImagePager
    lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent : Intent = Intent(this, LoadingActivity::class.java)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.imageView_journal) as ViewPager
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
