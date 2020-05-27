package com.CCU.kyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_progress.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        ProgressBar_pdf.visibility= View.VISIBLE
        val downLoader : PdfDownLoader = PdfDownLoader(intent.getStringExtra("url"),ProgressBar_pdf,this)
        downLoader.execute()
    }
}