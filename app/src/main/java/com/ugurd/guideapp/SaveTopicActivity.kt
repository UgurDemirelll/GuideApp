package com.ugurd.guideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_save_topic.*
import kotlinx.android.synthetic.main.fragment_edit_save.*

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
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val edit = findViewById(R.id.textTopicSelect) as TextView
                if(topic[spinner2.selectedItemPosition] == "Yeni Konu Ekle"){
                    edit.isEnabled = true
                    edit.setText("")
                    edit.setHint("Yeni Konu Ekle")
                    println("if çalışıyor mu")
                }else{
                    edit.setText(topic[spinner2.selectedItemPosition])
                    edit.isEnabled = false
                    println("else çalışıyor mu")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    fun backToMain(view: View){
        val intent = Intent(this@SaveTopicActivity,MainActivity::class.java)
        startActivity(intent)

    }
    fun forward(view: View){
        val intent = Intent(this@SaveTopicActivity,SaveActivity::class.java)
        val putTopicName = textTopicSelect.text.toString()
        val putTopicIssue = textTopicIssue.text.toString()
        println("ileri butonuna tıklandığında topic name: $putTopicName")
        intent.putExtra("topicname",putTopicName)
        intent.putExtra("issuename",putTopicIssue)
        startActivity(intent)
    }
}