package com.CCU.kyapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.prnd.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_major.*
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        YouTubePlayerView_major.play("UTx1igNpTpk")
        RelativeLayout_pdf.setOnClickListener {getUrl()}
    }
    private fun getUrl() : Unit{
        val intent = Intent(this, LoadingActivity::class.java)
        intent.putExtra("url","http://ipsi.konyang.ac.kr/cmm/fms/FileDown.do?atchFileId=FILE_000000000154242&fileSn=0")
        startActivity(intent)
    }

}
