package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.auth.FirebaseDownloader
import kotlinx.android.synthetic.main.activity_school.*

/**
 * introduce school information page
 *
 * @author Moonseok Choi
 * @version 1.0 make view and set event
 */
class SchoolActivity : AppCompatActivity() {
    private val pdf : PdfOpener =
        PdfOpener(this)
    private val download : FirebaseDownloader = FirebaseDownloader( "promote_pdf/prime_promote.pdf")
    /*
    set external pdf viewer
     */
    private var pdfChooser : Intent = Intent(Intent.ACTION_VIEW)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)
        val tb = Toolbar_school
        setSupportActionBar(tb)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowTitleEnabled(false)
        RelativeLayout_pdf.setOnClickListener {
            pdf.openPdf(pdfChooser,download.uri)
        startActivity(pdfChooser)}
    }

    override fun onStart(){
        super.onStart()
        YouTubePlayerView_major.play("UTx1igNpTpk")
        /*
        download pdf file to fire base
         */
        download.downloadToFirebase()
    }
    /**
     * when touch the back button on toolbar move to previous page
     *
     * @param MenuItem item item of toolbar (like back button)
     * @return boolean when find item return onOptionItemSelected if not return false
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("BackButton", "Item Id " + item.itemId)
        Log.d("Home", "id :"+ R.id.home)
        when(item.itemId){
            android.R.id.home -> {finish()
                download.deleteUser()
                return true}
            else ->{
                Log.e("BackButton","Cant find ID")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
