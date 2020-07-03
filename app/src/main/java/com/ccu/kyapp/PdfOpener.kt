package com.ccu.kyapp

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

/**
 *  open pdf
 *
 *  @author MoonSeok Choi
 *  @version 1.0 add function openPdf
 *  @since 2020.06.25
 */
class PdfOpener constructor(private var context: AppCompatActivity) {

    /**
     * get uri and set pdf intent
     *
     * @param Intent intent set intent Active pdf Viewer
     * @param Uri pdf files
     *
     * @return None
     */
    fun openPdf(intent:Intent, uri: Uri){

        intent.setDataAndType(uri,"application/pdf")
        val activities : List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
        try{
            context.startActivity(intent)
        }catch(e:Exception){
            Toast.makeText(context, "PDF 파일을 보기 위한 뷰어앱이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}