package com.ccu.kyapp

import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import kotlinx.android.synthetic.main.activity_progress.*
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.auth.ImageDownloadTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException



/**
 *  before loading something to page, this page is actual loading content
 *
 *  @author MoonSeok Choi
 *  @version 0.1 loading img to firebase
 *  @version 0.2 change code load to firebase
<<<<<<< HEAD
 *  @version 0.3 load to firebase change to background
=======
 *  @version 0.3 add admission information load to fire base
>>>>>>> dcd9352ba9edc67dbbd681f8f673e4a71dd4590c
 *  @since 2020.06.25
 */
class LoadingActivity : AppCompatActivity() {
    private lateinit var itent : Intent
    private var urls = ArrayList<String>()
    private val admission = ArrayList<String>()
    /*
      set auth firebase
       */
    private lateinit var auth : FireBaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        var it : Intent = intent
        val major = it.getStringExtra("major")
        val selector = it.getStringExtra("select")
        var auth : FireBaseAuth = FireBaseAuth(major,this)
        ProgressBar.visibility= View.VISIBLE
        it = Intent(this, MajorIntroActivity::class.java)
        lifecycleScope.launch{
            whenCreated {
                val urls = withContext(this.coroutineContext) {
                    val tmp : ArrayList<String> = auth.makePathList()
                    delay(2000)
                    auth.sortUrls(tmp)
                }
                when(selector){
                    "major" -> it.putExtra("Urls", urls)
                    "admission" -> {
                        if(auth.admission.isEmpty()){
                            it.setClass(this@LoadingActivity, AdmissionActivity::class.java)
                            startActivity(it)
                        }
                        it.putExtra("Urls",auth.admission)
                    }
                    else -> 0
                }
            }
            whenStarted {
                delay(300)
                startActivity(it)
            }
        }

    }


    override fun onResume() {
        super.onResume()

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






