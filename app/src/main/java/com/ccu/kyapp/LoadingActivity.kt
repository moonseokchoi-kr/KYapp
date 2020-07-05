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
import com.ccu.kyapp.auth.FireBaseAuth
import kotlinx.android.synthetic.main.activity_progress.*
import kotlinx.coroutines.*
import java.lang.ClassCastException

/**
 *  before loading something to page, this page is actual loading content
 *
 *  @author MoonSeok Choi
 *  @version 0.1 loading img to firebase
 *  @version 0.2 change code load to firebase
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
        val selcetor = it.getStringExtra("select")
        /*
        set auth firebase
         */
        val auth : FireBaseAuth = FireBaseAuth(major!!,this)
        /*
        loading view
         */
        ProgressBar_pdf.visibility= View.VISIBLE
        it = Intent(this, MajorIntroActivity::class.java)
        /*
        load img to firebase
        todo change the download task for firebase
         */
        lifecycleScope.launch{
            whenCreated {
                try{
                    val urls = withContext(this.coroutineContext) {
                        auth.makePathList()

                    }
                    delay(1000)
                    when(selcetor){
                        "major" -> it.putExtra("Urls", auth.sortUrls(urls))
                        "admission" -> it.putExtra("Urls", auth.admission)
                        else -> 0
                    }
                }catch(e:ClassCastException){
                    Toast.makeText(applicationContext,"Check the Internet Connection",Toast.LENGTH_LONG).show()
                }

            }
            whenStarted {
                startActivity(it)
            }
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