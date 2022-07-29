package com.ugurd.guideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun displayClick(view: View){
        val intent = Intent(this@MainActivity,DisplaySelectActivity::class.java)
        startActivity(intent)
    }

    fun editClick(view: View){
        val intent = Intent(applicationContext,EditTopicSelectActivity::class.java)
        startActivity(intent)
    }
    fun saveClick(view: View){
        val intent = Intent(applicationContext,SaveTopicActivity::class.java)
        startActivity(intent)
    }


}