package com.ugurd.guideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_save_topic.*

class SaveTopicActivity : AppCompatActivity() {

    private val topic = ArrayList<String>()
    private lateinit var veriadaptor : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_topic)

        topic.add("Yeni Konu Ekle")
        topic.add("yada ekleme")
        veriadaptor = ArrayAdapter(this@SaveTopicActivity,android.R.layout.simple_list_item_2,android.R.id.text2,topic)
        spinner2.adapter = veriadaptor

    }
    fun backToMain(view: View){
        val intent = Intent(this@SaveTopicActivity,MainActivity::class.java)
        startActivity(intent)


    }
    fun forward(view: View){
        val intent = Intent(this@SaveTopicActivity,SaveActivity::class.java)
        val putTopicName = topic[spinner2.selectedItemPosition]
        intent.putExtra("topicname",putTopicName)
        startActivity(intent)
    }
}