package com.ccu.kyapp.auth

import android.os.AsyncTask
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import java.util.LinkedHashMap

class ImageDownloadTask(var urls : ArrayList<String>, val admission : ArrayList<String>) : AsyncTask<String,Unit,ArrayList<String>>(){
    override fun doInBackground(vararg params: String?): ArrayList<String> {
        val mStorageRef = FirebaseStorage.getInstance().reference
        var size = 0
        var count = 0
        mStorageRef?.child("/$params")?.listAll()?.addOnSuccessListener { it ->
            size = it.items.size
            it.items.forEach { it ->
                it.downloadUrl.addOnSuccessListener {
                    Log.d("image uri","$it")
                    val url = it.toString()
                    urls.add(url)
                    count++
                    Log.d("Count of uris", "$count")
                }.addOnFailureListener{
                    Log.e("Error","FireBase Failure")
                }

            }

        }
        if(count != size)
            Thread.sleep(2000)
        return urls
    }

    override fun onPostExecute(result: ArrayList<String>?) {
        super.onPostExecute(result)
        if (result != null) {
            if (result.isEmpty()) {
                for(url in result){
                    val reg = """2F\d{0,3}""".toRegex()
                    val matchResult = reg.find(url)
                    Log.d("phase url", matchResult!!.value)
                    if(matchResult!!.value == "2F"){
                        admission.add(url)
                        continue
                    }
                }
            }
            result.sort()
            urls = result
        }
        admission.sort()
    }

}