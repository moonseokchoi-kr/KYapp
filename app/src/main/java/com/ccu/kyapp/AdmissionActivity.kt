package com.ccu.kyapp

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.auth.FireBaseAuth
import kotlinx.android.synthetic.main.activity_admission.*

class AdmissionActivity : AppCompatActivity() {
    private var pdfOpener : PdfOpener =
        PdfOpener(this)
    private val auth : FireBaseAuth = FireBaseAuth("admission_pdf/subject_info.pdf", this)
    private var isOpen : Boolean = false
    private var isOpenImg : Boolean = false
    var transition : LayoutTransition = LayoutTransition()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admission)
        var admissionGroup : ViewGroup = linearLayout_admission
        val tb = Toolbar_admission
        setSupportActionBar(tb)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.title=""
        relativeLayout_susi.setOnClickListener {
            transition.addChild(admissionGroup, linearLayout_susi)
            transition.addChild(admissionGroup, relativeLayout_subject)
            transition.addChild(admissionGroup, relativeLayout_all)
            if(!isOpen) {
                linearLayout_susi.visibility = View.VISIBLE
                isOpen = true
                enableLayoutTransitions()
            }
            else{
                enableLayoutTransitions()
                linearLayout_susi.visibility=View.GONE
                linearLayout_subject.visibility=View.GONE
                isOpen = false
                isOpenImg = false
            }

        }
        relativeLayout_subject.setOnClickListener{
            if(!isOpenImg){
                linearLayout_subject.visibility=View.VISIBLE
                isOpenImg = true
            }
            else{
                linearLayout_subject.visibility=View.GONE
                isOpenImg = false
            }
        }
        relativeLayout_subject_info.setOnClickListener{
            var intent:Intent = Intent(Intent.ACTION_VIEW)
            pdfOpener.openPdf(intent,auth.uri)
        }

    }
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
    override fun onStart() {
        super.onStart()
        auth.authFirebase()
    }
    private fun enableLayoutTransitions(){
        transition.enableTransitionType(LayoutTransition.APPEARING)
        transition.enableTransitionType(LayoutTransition.DISAPPEARING)
        transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING)
        transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
    }
}