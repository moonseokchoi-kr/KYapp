package com.ccu.kyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.auth.FirebaseDownloader
import com.ccu.kyapp.auth.FirebaseService.Companion.IMAGE_DOWNLOADING
import kotlinx.android.synthetic.main.activity_progress.*
import java.lang.Exception

/**
 *  before loading something to page, this page is actual loading content
 *
 *  @author MoonSeok Choi
 *  @version 0.1 loading img to firebase
 *  @version 0.2 change code load to firebase
 *  @version 0.3 load to firebase change to background
 *  @since 2020.06.25
 */
class LoadingActivity : AppCompatActivity() {
    lateinit var it : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        /*
        set intent previous page
         */
        it  = intent
        /*
        set string name of major
         */
        val major = it.getStringExtra("major")
        /*
        set auth firebase
         */
        val downloader  = FirebaseDownloader(major)
        downloader.enqueueWork(this, Intent().setAction(IMAGE_DOWNLOADING))
        it = Intent(this, MajorIntroActivity::class.java)
        /*
        loading view
         */
        ProgressBar.visibility= View.VISIBLE
        registerReceiver(downloadReceiver, IntentFilter(IMAGE_DOWNLOADING))

        /*
        load img to firebase
        todo change the download task for firebase
         */

    }

    override fun onResume() {
        super.onResume()
        registerReceiver(downloadReceiver, IntentFilter())
    }

    override fun onPause() {
        super.onPause()
        try{
            unregisterReceiver(downloadReceiver)
        }catch (e:Exception){

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try{
            unregisterReceiver(downloadReceiver)
        }catch (e:Exception){

        }
    }
    /**
     * don't active back Button
     *
     * @param None
     * @return None
     */
    override fun onBackPressed() {

    }

   private val downloadReceiver : BroadcastReceiver = object:BroadcastReceiver(){
       override fun onReceive(context: Context?, intent: Intent?) {
           if(intent?.extras != null){
               Log.d("broadCast", "broadcasting")
               it.putExtra("Urls", intent.extras!!.getStringArrayList("Urls"))
               startActivity(it)
           }

       }

   }
}