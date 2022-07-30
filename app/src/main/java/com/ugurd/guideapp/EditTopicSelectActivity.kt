package com.ugurd.guideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_display_select.*
import kotlinx.android.synthetic.main.activity_edit_topic_select.*
import kotlinx.android.synthetic.main.activity_save_topic.*

class EditTopicSelectActivity : AppCompatActivity() {

    private val topic = ArrayList<String>()
    private lateinit var veriAdaptoru: ArrayAdapter<String>
    private  val topicContent = ArrayList<String>()
    private lateinit var veriAdaptorContent: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_topic_select)
        topic.add("Konu Seç")
        topicContent.add("Ayraç Seç")
        veriAdaptoru = ArrayAdapter(this@EditTopicSelectActivity,android.R.layout.simple_list_item_1,android.R.id.text1,topic)
        veriAdaptorContent = ArrayAdapter(this@EditTopicSelectActivity,android.R.layout.simple_list_item_1,android.R.id.text1,topicContent)

        spinnerEditTopic.adapter = veriAdaptoru
        spinnerEditTopic2.adapter = veriAdaptorContent


        buttonEditTopic.setOnClickListener {
            val intent = Intent(this@EditTopicSelectActivity,EditSaveFragment::class.java)
            intent.putExtra("topicname",topic[spinnerEditTopic.selectedItemPosition])
            startActivity(intent)
        }

    }

}