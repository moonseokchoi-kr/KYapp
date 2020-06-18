package com.CCU.kyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_major.*
import kotlinx.android.synthetic.main.activity_pdfview.*


class Adapdet : AppCompatActivity() {
    private val mStorageRef: StorageReference? = FirebaseStorage.getInstance().reference;
    var url = ""
    private val mAuth = FirebaseAuth.getInstance()
    private val user : FirebaseUser? = mAuth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent : Intent = Intent(this, LoadingActivity::class.java)
        setContentView(R.layout.activity_major)
        YouTubePlayerView_major.play("UTx1igNpTpk")
        RelativeLayout_pdf.setOnClickListener {intent.putExtra("url" , url)
            startActivity(intent)}
    }

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

}
