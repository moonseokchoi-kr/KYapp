package com.ccu.kyapp.auth

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception
import java.net.URI

class FireBaseAuth constructor(var path: String, var context: AppCompatActivity){
    private val mStorageRef: StorageReference? = FirebaseStorage.getInstance().reference;
    private val mAuth = FirebaseAuth.getInstance()
    private val user : FirebaseUser? = mAuth.currentUser
    lateinit var uri : Uri
    fun authFirebase(){
        if(user != null){
            mStorageRef?.child(path)?.downloadUrl
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
                mStorageRef?.child(path)?.downloadUrl?.addOnSuccessListener { uri=it
                    Log.d("downLoad Uri", "uri :$uri")}?.addOnFailureListener{
                    Log.e("Error" , "URLDownload Failure:Firebase"+ it.printStackTrace())
                }
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