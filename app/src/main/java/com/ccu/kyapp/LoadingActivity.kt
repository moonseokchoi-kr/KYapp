package com.ccu.kyapp

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import com.ccu.kyapp.auth.FireBaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_progress.*
import kotlinx.coroutines.*
import java.lang.ClassCastException
import kotlin.coroutines.CoroutineContext

/**
 *  before loading something to page, this page is actual loading content
 *
 *  @author MoonSeok Choi
 *  @version 0.1 loading img to firebase
 *  @version 0.2 change code load to firebase
 *  @version 0.3 add admission information load to fire base
 *  @since 2020.06.25
 */
class LoadingActivity : AppCompatActivity() {

    lateinit var urls : ArrayList<String>
    private lateinit var itent : Intent
    /*
      set auth firebase
       */
    private lateinit var auth : FireBaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        ProgressBar_pdf.visibility= View.VISIBLE
        /*
        set intent previous page
         */
        itent = intent
        /*
        set string name of major
         */
        val major = itent.getStringExtra("major")


        auth = FireBaseAuth(major,this)
        urls = auth.makePathList()
    }

    override fun onResume() {
        super.onResume()

        /*
        loading view
         */
        itent = Intent(this, MajorIntroActivity::class.java)
        val selector = itent.getStringExtra("select")
        /*
        load img to firebase
        todo change the download task for firebase
         */
        try{
            if(auth.count == auth.size){
                auth.sortUrls(urls)
                when(selector){
                    "major" -> itent.putExtra("Urls",urls)
                    "admission" -> itent.putExtra("Urls",urls)
                }
                startActivity(itent)
            }

        }
        catch(e:ClassCastException) {
            Toast.makeText(applicationContext, "Check the Internet Connection", Toast.LENGTH_LONG)
                .show()
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

}
