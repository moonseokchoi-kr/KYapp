package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var hd : Handler = Handler()
        hd.postDelayed(Runnable { var intent = Intent(this,
            MainActivity::class.java)
            startActivity(intent)
        this.finish()},3000)
    }

    override fun onBackPressed() {
    }
}