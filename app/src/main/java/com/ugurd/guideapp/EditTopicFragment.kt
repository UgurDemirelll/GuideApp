package com.ugurd.guideapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_edit_topic_select.*
import kotlinx.android.synthetic.main.fragment_edit_topic.view.*

class EditTopicFragment : Fragment() {

    private val topic = ArrayList<String>()
    private lateinit var veriAdaptoru: ArrayAdapter<String>
    private val topicContent = ArrayList<String>()
    private lateinit var veriAdaptorContent: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_edit_topic)
        /*topic.add("Konu Seç")
        topicContent.add("Ayraç Seç")
        veriAdaptoru = ArrayAdapter(
            this@EditTopicFragment,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            topic
        )
        veriAdaptorContent = ArrayAdapter(
            this@EditTopicFragment,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            topicContent
        )
        spinnerEditTopic.adapter = veriAdaptoru


         */


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_topic, container, false)


        view.buttonEditTopic.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_editTopicFragment_to_editSaveListFragment)


        }
        return view
    }
}