package com.ccu.kyapp.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception
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

class FirebaseDownloader ()  : FirebaseService(){
    private val downloaderScope = CoroutineScope(Dispatchers.Main)
    private var path : String = ""
    constructor(path : String):this(){
        this.path = path
    }

    /*
    save the uri
     */
    lateinit var uri : Uri
    /*
    save the path list
     */
    private var pathList : ArrayList<String> =ArrayList()

    override fun onHandleWork(intent: Intent) {
        super.onHandleWork(intent)

        when(intent.action){
            FILE_DOWNLOADING ->{
                downloadToFirebase()
                sendBroadcast(Intent().setAction(intent.action).putExtra("Urls",uri))
            }
            IMAGE_DOWNLOADING ->{
                Log.d("path", path)
                downloaderScope.launch {
                    makePathList()
                    while(pathList.isEmpty())
                        delay(1000)
                    sendBroadcast(Intent().setAction(intent.action).putExtra("Urls",sortUrls(pathList)))
                }
            }
        }
    }


    /**
     * make path list of image files at firebase storage
     * certify use [signInAnonymously]
     * @param None
     * @return None
     */
    private suspend fun makePathList () = withContext(Dispatchers.IO){
        val uris = ArrayList<String>()
            mStorageRef?.child("/$path")?.listAll()?.addOnSuccessListener { it ->
                it.items.forEach { it ->
                    it.downloadUrl.addOnSuccessListener {
                        Log.d("image uri","$it")
                        val url = it.toString()
                        pathList.add(url)
                        Log.d("Count of uris", "${uris.size}")
                    }.addOnFailureListener{
                        Log.e("Error","FireBase Failure")
                    }
                }
            }
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

    /**
     * after make file path list sort urls in list
     *
     * @param urls urls list
     * @return Arraylist list return to sorting list
     */
    private fun sortUrls (urls:ArrayList<String>) : ArrayList<String> {
        val map = phaseFilename(urls)
        return map.values.toList() as ArrayList<String>
    }
    /**
     * phase file name and return map
     *
     * @param urls list of url
     * @return sortedMap
     */
    private fun phaseFilename (urls : ArrayList<String>) : Map<Int,String>{

        val map = mutableMapOf<Int,String>()
        for(url in urls){
            val reg = """2F\d{0,3}""".toRegex()
            val matchResult = reg.find(url)
            Log.d("phase url", matchResult!!.value)
            if(matchResult.value == "2F"){
                //admission.add(url)
                //admission.sort()
                continue
            }
            val index = Integer.parseInt(matchResult.value.replace("2F",""))
            map[index] = url
        }
        val sortMap : MutableMap<Int,String> = LinkedHashMap<Int,String>()
        map.keys.sorted().forEach{ sortMap[it] = map[it]!!}
        return sortMap
    }


}