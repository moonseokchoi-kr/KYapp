package com.ccu.kyapp

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_progress.*
import com.ccu.kyapp.auth.FireBaseAuth
import com.ccu.kyapp.auth.ImageDownloadTask
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
        ProgressBar.visibility= View.VISIBLE
        /*
        set intent previous page
         */
        itent = intent

        /*
        set string name of major
         */
        val major = itent.getStringExtra("major")
        /*
        set auth firebase
         */
        itent = Intent(this, MajorIntroActivity::class.java)
        /*
        loading view
         */
        ProgressBar.visibility= View.VISIBLE

        ImageDownloadTask(urls, admission).execute(major)

        //auth = FireBaseAuth(major,this)
        //urls = auth.makePathList()
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
                when(selector){
                    "major" -> itent.putExtra("Urls",urls)
                    "admission" -> itent.putExtra("Urls",urls)
                }
                //startActivity(itent)
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






