package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import com.ccu.kyapp.auth.FireBaseDownload
import kotlinx.android.synthetic.main.activity_progress.*
import kotlinx.coroutines.*
import java.lang.ClassCastException

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        /*
        set intent previous page
         */
        var it : Intent = intent
        /*
        set string name of major
         */
        val major = it.getStringExtra("major")
        /*
        set auth firebase
         */
        val download : FireBaseDownload = FireBaseDownload(major!!,this)
        /*
        loading view
         */
        ProgressBar.visibility= View.VISIBLE
        it = Intent(this, MajorIntroActivity::class.java)
        /*
        load img to firebase
        todo change the download task for firebase
         */


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