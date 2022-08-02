package com.ugurd.guideapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.Navigation
import com.ugurd.guideapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_display_select.*
import kotlinx.android.synthetic.main.activity_display_select.view.*
import kotlinx.android.synthetic.main.activity_edit_topic_select.*
import kotlinx.android.synthetic.main.fragment_edit_topic.view.*

class EditTopicFragment : Fragment() {


    private val topic = ArrayList<String>()
    private lateinit var veriAdaptoru: ArrayAdapter<String>
    private val topicContent = ArrayList<String>()
    private lateinit var veriAdaptorContent: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_topic, container, false)
        try {
            activity?.let {

                topic.add("Konu Seçiniz")
                veriAdaptoru = ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, topic)
                println("try buraya kadar çalıştı")
                view.spinnerEditTopic.adapter = veriAdaptoru

        }
            }catch (e:Exception){
                e.printStackTrace()
            }
        view.buttonEditTopic.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_editTopicFragment_to_editSaveListFragment)
        }
        return view
    }

}