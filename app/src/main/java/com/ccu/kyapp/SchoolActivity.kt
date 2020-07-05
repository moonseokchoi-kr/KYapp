package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.auth.FireBaseAuth
import kotlinx.android.synthetic.main.activity_school.*

/**
 * introduce school information page
 *
 * @author Moonseok Choi
 * @version 0.1 make view and set event
 * @version 0.2 change the videoID
 */
class SchoolActivity : AppCompatActivity() {
    private val pdf : PdfOpener =
        PdfOpener(this)
    private val auth : FireBaseAuth = FireBaseAuth( "school/promotion.pdf", this)
    /*
    set external pdf viewer
     */
    private var pdfChooser : Intent = Intent(Intent.ACTION_VIEW)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)
        YouTubePlayerView_major.play("7DMAP3p62RA")
        val tb = Toolbar_school
        setSupportActionBar(tb)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowTitleEnabled(false)
        RelativeLayout_pdf.setOnClickListener {
            pdf.openPdf(pdfChooser,auth.uri)
        startActivity(pdfChooser)}
    }

    override fun onStart(){
        super.onStart()

        /*
        download pdf file to fire base
         */
        auth.downloadToFirebase()
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
                auth.deleteUser()
                return true}
            else ->{
                Log.e("BackButton","Cant find ID")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
