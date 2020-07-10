package com.ccu.kyapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_major_select.*

/**
 * select major page
 *
 * click major component set string in intent
 *
 * @author Moonseok Choi
 * @version 0.1 make major component and set click event
 * @version 0.2 add click cell function to videoID at database
 * @since 2020.06.26
 */
class MajorSelectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major_select)
        /*
        set toolbar
         */
        val tb = Toolbar_select
        setSupportActionBar(tb)
        val ab : ActionBar? = supportActionBar
        ab?.setDisplayShowTitleEnabled(false)
        ab?.setDisplayHomeAsUpEnabled(true)
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

    /**
     * make map for major page
     *
     * @param None
     * @return None
     */
    private fun getData():Map<String,String>{
        val data = mutableMapOf<String,String>()
        data["relativeLayout_gfs"] = "gfs"
        data["relativeLayout_beauty"] = "beauty"
        data["relativeLayout_software"] = "software"
        data["relativeLayout_bio"] = "bio"
        data["relativeLayout_security"] = "security"
        data["relativeLayout_it"] = "it"
        data["relativeLayout_machine"] = "machine"
        data["relativeLayout_clinical"] = "clinical"
        data["relativeLayout_design"] = "design"
        data["relativeLayout_extinguish"] = "extinguish"

        return data
    }

    /**
     * click the cell of tablelayout move to majorpage
     *
     * @param View v view of tableLayout
     * @return None
     */
    fun clickHandlerCell(v:View){
        val tableData = getData()
        val major = tableData[v.tag]
        val intent:Intent = Intent(this, MajorActivity::class.java)
        //major data don't exist in map don't start activity
        if(major == null)
            return
        intent.putExtra("major", major)
        var videoID = ""
        val database = FirebaseFirestore.getInstance()
        database.collection("major").get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                task.result?.forEach {
                    if(it.id == major){
                        videoID = it.data["videoID"].toString()
                        intent.putExtra("videoID",videoID)
                        startActivity(intent)
                    }
                }
            }else{
                Log.w(ContentValues.TAG, "Error getting documents.", task.exception);
            }

        }

    }



}