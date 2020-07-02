package com.ccu.kyapp

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.majorImage.ImagePagerUri
import kotlinx.android.synthetic.main.intro_tab.*

/**
 * show intro image for major
 *
 * @author Moonseok Choi
 * @version 0.1 create class and override onCreate
 * @since 2020.07.02
 */
class MajorIntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.intro_tab)
        val intent = intent
        val imgUrls = intent.getStringArrayListExtra("Urls")
        viewPager2_major.adapter = ImagePagerUri(imgUrls)
        /* set full screen */
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}