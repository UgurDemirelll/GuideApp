package com.ugurd.guideapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    lateinit var newIntent: Intent

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = this.openOrCreateDatabase("Topics",Context.MODE_PRIVATE,null)

        val cursor = database.rawQuery("SELECT * FROM topics",null)
        val topicName = cursor.getColumnIndex("topicName")
        val topicIssue = cursor.getColumnIndex("topicIssue")
        val topicExplanation = cursor.getColumnIndex("topicExplanation")
        while(cursor.moveToNext()) {

            println("topicname : ${cursor.getString(topicName)}")
            //println("issue : ${cursor.getString(topicIssue)}")
            //println("explanation : ${cursor.getString(topicExplanation)}")
        }
        cursor.close()






    }
    fun displayClick(view: View){
        val intent = Intent(this@MainActivity,DisplaySelectActivity::class.java)
        startActivity(intent)
    }

    fun editClick(view: View){
        val intent = Intent(applicationContext,EditSaveActivity::class.java)
        startActivity(intent)
    }
    fun saveClick(view: View){
        val intent = Intent(applicationContext,SaveTopicActivity::class.java)
        startActivity(intent)
    }


}