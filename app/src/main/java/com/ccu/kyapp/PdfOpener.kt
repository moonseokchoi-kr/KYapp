package com.ccu.kyapp

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.auth.FireBaseAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception

class PdfOpener constructor(private var context: AppCompatActivity, var path:String) {

    private val auth = FireBaseAuth(path,context)
    fun getAuth (): FireBaseAuth{
        return auth
    }
    fun openPdf(intent:Intent){
        auth.authFirebase()
        intent.setDataAndType(auth.uri,"application/pdf")
        val activities : List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
        try{
            context.startActivity(intent)
        }catch(e:Exception){
            Toast.makeText(context, "PDF 파일을 보기 위한 뷰어앱이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}