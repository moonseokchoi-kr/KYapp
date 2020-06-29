package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_major_select.*

/**
 * select major page
 *
 * click major component set string in intent
 *
 * @author Moonseok Choi
 * @version 1.0 make major component and set click event
 * @since 2020.06.26
 */
class MajorSelectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major_select)
        /*
        set next page
         */
        val intent : Intent = Intent(this, LoadingActivity::class.java)
        /*
        set toolbar
         */
        val tb = Toolbar_select
        setSupportActionBar(tb)
        val ab : ActionBar? = supportActionBar
        ab!!.title = ""
        ab.setDisplayHomeAsUpEnabled(true)
        /*
        click event
         */
        relativeLayout_clinical.setOnClickListener { intent.putExtra("major","clinical")
            startActivity(intent)
        }
        relativeLayout_beauty.setOnClickListener {
            intent.putExtra("major", "beauty")
            startActivity(intent)
        }
        relativeLayout_software.setOnClickListener {
            intent.putExtra("major","software")
            startActivity(intent)
        }
        relativeLayout_gfs.setOnClickListener {
            intent.putExtra("major","gfs")
            startActivity(intent)
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
                return true}
            else ->{
                Log.e("BackButton","Cant find ID")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}