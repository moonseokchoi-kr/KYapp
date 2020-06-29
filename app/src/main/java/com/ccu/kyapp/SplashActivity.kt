package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

/**
 * display splash view
 *
 * @author MoonSeok Choi
 * @version 1.0 create splash view
 * @since 2020.06.25
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /*
        active splash view
         */
        val hd : Handler = Handler()
        hd.postDelayed(Runnable { val intent = Intent(this,
            MainActivity::class.java)
            startActivity(intent)
        this.finish()},3000)
    }

    /**
     * don't active back button
     *
     * @param None
     * @return None
     */
    override fun onBackPressed() {
    }
}