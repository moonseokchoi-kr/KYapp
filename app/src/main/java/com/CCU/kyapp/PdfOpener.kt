package com.CCU.kyapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.listener.OnErrorListener
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_pdfview.*
import java.io.File
import java.lang.Exception

class PdfOpener constructor(private var context: AppCompatActivity) {
    private val mStorageRef: StorageReference? = FirebaseStorage.getInstance().reference;
    private val mAuth = FirebaseAuth.getInstance()
    private var url =""
    private val user : FirebaseUser? = mAuth.currentUser
    fun authFirebase(){
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
            .addOnSuccessListener(context) {
                url =  mStorageRef?.child("promote_pdf/prime_promote.pdf")?.downloadUrl.toString()
            }
            .addOnFailureListener(
                context
            ) { exception -> Log.e("Login", "signInAnonymously:FAILURE", exception) }
    }
    fun deleteUser(){
        try{
            user?.delete()
        }catch (e : Exception){
            Log.e("Logout", "Error is Occur :" + e.printStackTrace())
        }

    }
}