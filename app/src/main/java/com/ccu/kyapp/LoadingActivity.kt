package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import com.ccu.kyapp.auth.FireBaseAuth
import kotlinx.android.synthetic.main.activity_progress.*
import kotlinx.coroutines.*

class LoadingActivity : AppCompatActivity() {
    private val job = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        var it : Intent = intent
        val major = it.getStringExtra("major")
        var auth : FireBaseAuth = FireBaseAuth(major,this)
        ProgressBar_pdf.visibility= View.VISIBLE
        it = Intent(this, MajorActivity::class.java)
        lifecycleScope.launch{
            whenCreated {
                var urls = withContext(this.coroutineContext) {
                    var tmp : ArrayList<String> = auth.makePathList()
                    delay(5000)
                    auth.sortUrls(tmp)
                }
                it.putExtra("Urls", urls)
                it.putExtra("major", major)
            }
            whenStarted {
                delay(300)
                startActivity(it)
            }
        }

    }


}