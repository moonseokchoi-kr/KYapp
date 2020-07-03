package com.ccu.kyapp

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.auth.FireBaseDownload
import kotlinx.android.synthetic.main.activity_admission.*

/**
 * This page shows information about entrance exams.
 * show entrance exams
 * open pdf file
 *
 * @author ChoiMoonSeok
 * @version 1.0 write script
 * @since 2020.6.26
 */
class AdmissionActivity : AppCompatActivity() {
    /*
    instance for open pdf
     */
    private val pdfOpener : PdfOpener = PdfOpener(this)
    /*
    auth firebase and download
     */
    private val download : FireBaseDownload = FireBaseDownload("admission_pdf/subject_info.pdf", this)
    /*
    toggle layout
     */
    private var isOpen : Boolean = false
    /*
    toggle layout
     */
    private var isOpenImg : Boolean = false
    /*
     transit layout
     */
    private val transition : LayoutTransition = LayoutTransition()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admission)
        /*
         set viewGroup child that animate something
         */
        val admissionGroup : ViewGroup = linearLayout_admission
        val tb = Toolbar_admission
        setSupportActionBar(tb)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""
        /*
        click layout animate layouts
         */
        relativeLayout_susi.setOnClickListener {
            transition.addChild(admissionGroup, linearLayout_susi)
            transition.addChild(admissionGroup, relativeLayout_subject)
            transition.addChild(admissionGroup, relativeLayout_all)
            // open
            if(!isOpen) {
                linearLayout_susi.visibility = View.VISIBLE
                isOpen = true
                enableLayoutTransitions()
            }
            //close
            else{
                enableLayoutTransitions()
                linearLayout_susi.visibility=View.GONE
                linearLayout_subject.visibility=View.GONE
                isOpen = false
                isOpenImg = false
            }

        }
        relativeLayout_subject.setOnClickListener{
            //open
            if(!isOpenImg){
                linearLayout_subject.visibility=View.VISIBLE
                isOpenImg = true
            }
            //close
            else{
                linearLayout_subject.visibility=View.GONE
                isOpenImg = false
            }
        }
        relativeLayout_all.setOnClickListener{
            //open
            if(!isOpenImg){
                linearLayout_all.visibility=View.VISIBLE
                isOpenImg = true
            }
            //close
            else{
                linearLayout_all.visibility=View.GONE
                isOpenImg = false
            }
        }
        relativeLayout_ex.setOnClickListener{
            //open
            if(!isOpenImg){
                linearLayout_ex.visibility=View.VISIBLE
                isOpenImg = true
            }
            //close
            else{
                linearLayout_ex.visibility=View.GONE
                isOpenImg = false
            }
        }
        relativeLayout_subject_info.setOnClickListener{
            val intent:Intent = Intent(Intent.ACTION_VIEW)
            pdfOpener.openPdf(intent,download.uri)
        }

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
    override fun onStart() {
        super.onStart()
        download.downloadToFirebase()
    }

    /**
     * set animation when layout change
     *
     * @param None
     * @return None
     */
    private fun enableLayoutTransitions(){
        transition.enableTransitionType(LayoutTransition.APPEARING)
        transition.enableTransitionType(LayoutTransition.DISAPPEARING)
        transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING)
        transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
    }
}