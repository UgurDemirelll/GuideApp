package com.ugurd.guideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_display_select.*

class DisplaySelectActivity : AppCompatActivity() {

    private val topic = ArrayList<String>()
    private lateinit var veriAdaptoru:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_select)
        topic.add("deneme1")
        topic.add("deneme2")
        topic.add("deneme3")
        veriAdaptoru = ArrayAdapter(this@DisplaySelectActivity,android.R.layout.simple_list_item_1, android.R.id.text1, topic)
        spinner.adapter = veriAdaptoru

        buttonDisplayNext.setOnClickListener {
            val intent = Intent(applicationContext, DisplayActivity::class.java)
            startActivity(intent)
    }
    }
}