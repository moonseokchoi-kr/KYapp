package com.CCU.kyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_school.*


class SchoolActivity : AppCompatActivity() {
    private val pdf : PdfOpener = PdfOpener(this, "promote_pdf/prime_promote.pdf")
    private var pdfChooser : Intent = Intent(Intent.ACTION_VIEW)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)
        val tb = Toolbar_school
        setSupportActionBar(tb)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""
        YouTubePlayerView_major.play("UTx1igNpTpk")
        RelativeLayout_pdf.setOnClickListener {
            pdf.openPdf(pdfChooser)
        startActivity(pdfChooser)}
    }

    override fun onStart(){
        super.onStart()
        pdf.authFirebase()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("BackButton", "Item Id " + item.itemId)
        Log.d("Home", "id :"+R.id.home)
        when(item.itemId){
            android.R.id.home -> {finish()
                pdf.deleteUser()
                return true}
            else ->{
                Log.e("BackButton","Cant find ID")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
