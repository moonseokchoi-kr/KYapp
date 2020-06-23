package com.CCU.kyapp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AdmissionActivity : AppCompatActivity() {
    class activeClickListener constructor(var layout: LinearLayout) : View.OnClickListener {
        override fun onClick(v: View?) {
            
        }

    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }
}