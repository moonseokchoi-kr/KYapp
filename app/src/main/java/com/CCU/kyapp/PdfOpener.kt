package com.CCU.kyapp

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
    lateinit var uri : Uri
    private val user : FirebaseUser? = mAuth.currentUser


    fun authFirebase(){
        if(user != null){
            mStorageRef?.child("promote_pdf/prime_promote.pdf")?.downloadUrl
                ?.addOnSuccessListener { uri = it
                    Log.d("downLoad Uri", "uri :$uri")
                }
                ?.addOnFailureListener{ Log.e("Error" , "URLDownload Failure:Firebase") }
        }else{
            signInAnonymously();
        }
    }
    private fun signInAnonymously() {
        mAuth.signInAnonymously()
            .addOnSuccessListener(context) {
                Toast.makeText(context,"Login Successful!!", Toast.LENGTH_SHORT).show()
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

    fun openPdf(uri : Uri, intent:Intent){
        intent.setDataAndType(uri,"application/pdf")
        val activities : List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
        try{
            context.startActivity(intent)
        }catch(e:Exception){
            Toast.makeText(context, "PDF 파일을 보기 위한 뷰어앱이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}