package com.ccu.kyapp.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception


open class FirebaseService : JobIntentService() {

    val mStorageRef: StorageReference?
        get(){
            return FirebaseStorage.getInstance().reference
        }
     private val mAuth : FirebaseAuth
        get(){
            return FirebaseAuth.getInstance()
        }
     private val user : FirebaseUser?
        get(){
            return mAuth.currentUser
        }
    companion object{
        const val TAG = "AuthService"
        const val JOB_ID = 1001
        const val IMAGE_DOWNLOADING = "background_downloading"
        const val FILE_DOWNLOADING = "file_downloading"

    }
    fun enqueueWork(context: Context, work : Intent){
        enqueueWork(context, this::class.java, JOB_ID, work)
    }


    override fun onHandleWork(intent: Intent) {
        signInAnonymously()
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteUser()
    }

    /**
     * certify firebase anonymously
     *
     * @param None
     * @return None
     */
    private fun signInAnonymously() {
        mAuth.signInAnonymously()
            .addOnSuccessListener {

            }
            .addOnFailureListener() { exception -> Log.e("Login", "signInAnonymously:FAILURE", exception) }
    }

    /**
     * delete anonymously user
     *
     * @param None
     * @return None
     */
    fun deleteUser(){
        try{
            user?.delete()
        }catch (e : Exception){
            Log.e("Logout", "Error is Occur :" + e.printStackTrace())
        }

    }

}