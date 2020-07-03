package com.ccu.kyapp.auth

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
 *  @since 2020.06.25
 */

class FireBaseAuth constructor(var path: String, var context: AppCompatActivity){

    private val mStorageRef: StorageReference?
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
    /*
    save the uri
     */
    lateinit var uri : Uri
    //var admission : ArrayList<String> = ArrayList()
    /**
     * make path list of image files at firebase storage
     * certify use [signInAnonymously]
     * @param None
     * @return None
     */
    fun makePathList () : ArrayList<String>{
        val uris = ArrayList<String>()
        if(user != null){
            mStorageRef?.child("/$path")?.listAll()?.addOnSuccessListener { it ->
                it.items.forEach { it ->
                    it.downloadUrl.addOnSuccessListener(context) {
                        Log.d("image uri","$it")
                        val url = it.toString()
                        uris.add(url)
                        Log.d("Count of uris", "${uris.size}")
                    }.addOnFailureListener{
                        Log.e("Error","FireBase Failure")
                    }
                }
            }
        }
        else{
            signInAnonymously()
            makePathList()
        }
        return uris
    }

    /**
     * downloading to file at firebase storage like pdf, image
     * this function only one file download
     * certify use [signInAnonymously]
     * @param None
     * @return None
     */
    fun downloadToFirebase(){
        if(user != null){
            mStorageRef?.child(path)?.downloadUrl
                ?.addOnSuccessListener { uri = it
                    Log.d("downLoad Uri", "uri :$uri")
                }
                ?.addOnFailureListener{ Log.e("Error" , "URLDownload Failure:Firebase") }
        }else{
            signInAnonymously();
            downloadToFirebase()
        }
    }

    /**
     * certify firebase anonymously
     *
     * @param None
     * @return None
     */
    private fun signInAnonymously() {
        mAuth.signInAnonymously()
            .addOnSuccessListener(context) {
                Toast.makeText(context,"Login Successful!!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener(
                context
            ) { exception -> Log.e("Login", "signInAnonymously:FAILURE", exception) }
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

    /**
     * after make file path list sort urls in list
     *
     * @param urls urls list
     * @return Arraylist list return to sorting list
     */
    fun sortUrls(urls : ArrayList<String>):ArrayList<String>{
        val map = phaseFilename(urls)
        return map.values.toList() as ArrayList<String>
    }

    /**
     * phase file name and return map
     *
     * @param urls list of url
     * @return sortedMap
     */
    private fun phaseFilename (urls : ArrayList<String>): Map<Int,String>{
        val map = mutableMapOf<Int,String>()
        for(url in urls){
            val reg = """2F\d{0,3}""".toRegex()
            val matchResult = reg.find(url)
            Log.d("phase url", matchResult!!.value)
            if(matchResult!!.value == "2F"){
                //admission.add(url)
                //admission.sort()
                continue
            }
            val index = Integer.parseInt(matchResult!!.value.replace("2F",""))
            map[index] = url
        }
        val sortMap : MutableMap<Int,String> = LinkedHashMap<Int,String>()
        map.keys.sorted().forEach{ sortMap[it] = map[it]!!}
        return sortMap
    }


}