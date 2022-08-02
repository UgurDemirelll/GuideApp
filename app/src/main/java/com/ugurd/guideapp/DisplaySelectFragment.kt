package com.ugurd.guideapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_display_select.*
import kotlinx.android.synthetic.main.fragment_display_select.*
import kotlinx.android.synthetic.main.fragment_display_select.view.*
import kotlinx.android.synthetic.main.fragment_edit_topic.view.*

class DisplaySelectFragment : Fragment() {

    private val topic = ArrayList<String>()
    private lateinit var veriAdaptoru: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topic.clear()
        topic.add("Konu Seçiniz")
        context?.let {
            try {
                val database = it.openOrCreateDatabase("Topics", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS topics (id INTEGER PRIMARY KEY,topicName VARCHAR, topicIssue VARCHAR,topicExplanation VARCHAR,image BLOB)")
                val cursor = database.rawQuery("SELECT * FROM topics",null)
                val topicName = cursor.getColumnIndex("topicName")
                val topicId = cursor.getColumnIndex("id")
                println(cursor.getColumnIndex("topicName"))
                println(topicName)

                println(cursor.moveToNext())
                while (cursor.moveToNext()) {
                    topic.add(cursor.getString(topicName))
                    println("???")
                    println("id : ${cursor.getInt(topicId)}")
                    println("topicname : ${cursor.getString(topicName)}")
                }
                cursor.close()

                println("database açıldı")
            }catch (e:Exception){
                println("database açılmadı")
                e.printStackTrace()
            }
        }


        /*
          buttonDisplayNext2.setOnClickListener {
               val intent = Intent(context,DisplayFragment::class.java)
           }
        */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_select, container, false)


        context?.let {

            veriAdaptoru = ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, topic)
            view.spinner3.adapter = veriAdaptoru

        }




        return view


    }

}