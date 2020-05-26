package com.CCU.kyapp

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import java.io.*
import java.net.URL
import java.net.URLConnection

class PdfDownLoader constructor(val url: String, val progress: ProgressBar, val context: Context) : AsyncTask<Void,Void,String>() {

    var filepath: String = ""
    override fun onPreExecute() {
        super.onPreExecute()
        progress.progress = 0
        progress.max = 100
    }
    override fun doInBackground(vararg params: Void?): String? {
        val tmp:URL = URL(url)
        Log.d("Url",tmp.path)
        val connection : URLConnection = tmp.openConnection()
        connection.connect()
        val filename : String = "promote_prime"
        val lengthFilter : Int = connection.contentLength
        val total = 0
        val input : InputStream = BufferedInputStream(tmp.openStream());
        filepath = context.cacheDir.path +'/'+filename
        val data  = ByteArray(1024){0}
        val output : OutputStream = FileOutputStream(filepath)
        while(input.read(data) != -1){
            output.write(data)
        }
        output.flush()
        output.close()
        input.close()
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progress.progress =100
    }

}