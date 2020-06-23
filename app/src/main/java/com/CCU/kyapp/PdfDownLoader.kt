package com.CCU.kyapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import com.github.barteksc.pdfviewer.util.FileUtils
import com.google.firebase.storage.FileDownloadTask
import java.io.*
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class PdfDownLoader constructor(private val url: String, private val progress: ProgressBar, private val context: Context) : AsyncTask<Void,Void,String>() {

    var filepath: String = ""
    override fun onPreExecute() {
        super.onPreExecute()
        progress.progress = 0
        progress.max = 100
    }
    override fun doInBackground(vararg params: Void?): String? {
        val tmp:URL = URL(url)
        Log.d("PdfUrl",tmp.path)
        val connection : URLConnection = tmp.openConnection()
        connection.connect()
        val filename : String = "promote_prime.pdf"
        val total : Int = connection.contentLength
        //filepath = context.cacheDir.toString()
        filepath=context.filesDir.toString()
        val folder = File(filepath)
        folder.mkdir()
        val pdfFile = File(folder, filename)
        filepath = pdfFile.path
        try{
            pdfFile.createNewFile()
        }catch(e : Exception){
            e.printStackTrace()
        }
        val input : BufferedInputStream = BufferedInputStream(connection.getInputStream())
        val output : BufferedOutputStream = BufferedOutputStream(FileOutputStream(pdfFile))
        val buffer : ByteArray = ByteArray(1024){0}
        var t =0
        while(true){
            var count = input.read(buffer)
            if(count == -1)
                break;
            t+= count

            Log.d("DownloadFile","Progress : $t / $total")
            output.write(buffer,0,count)
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progress.progress=100
        val intent = Intent(context,PdfOpener::class.java)
        intent.putExtra("filepath",filepath)
        context.startActivity(intent)
    }

}