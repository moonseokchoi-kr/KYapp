package com.ccu.kyapp.auth

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

/**
 *  certify for firebase and download to firebase storage
 *
 *  @author MoonSeok Choi
 *  @version 0.1 create class and create functions
 *  @version 0.2 create function makePathList, phaseFilename, sortUrls
 *  @version 0.3 change dowload and auth fire base background
 *  @since 2020.06.25
 */

class FireBaseDownload constructor(var path: String, var context: AppCompatActivity) : FirebaseService(){

    /*
    save the uri
     */
    lateinit var uri : Uri
    //var admission : ArrayList<String> = ArrayList()

    override fun onHandleWork(intent: Intent) {
        super.onHandleWork(intent)
        downloadToFirebase()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * downloading to file at firebase storage like pdf, image
     * this function only one file download
     * certify use [signInAnonymously]
     * @param None
     * @return None
     */
    fun downloadToFirebase(){
        mStorageRef?.child(path)?.downloadUrl
            ?.addOnSuccessListener { uri = it
                Log.d("downLoad Uri", "uri :$uri")
            }
            ?.addOnFailureListener{ Log.e("Error" , "URLDownload Failure:Firebase") }

    }
    
}