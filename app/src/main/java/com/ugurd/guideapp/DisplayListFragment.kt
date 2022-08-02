package com.ugurd.guideapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_display_list.*

class DisplayListFragment : Fragment() {

    var topicNameList = ArrayList<String>()
    var topicIdList = ArrayList<Int>()
    private lateinit var listAdapter : DisplayListRcyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = DisplayListRcyclerAdapter(topicNameList,topicIdList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listAdapter

        sqldata()
    }

    fun sqldata(){
        try {
            activity?.let {
            val database = it.openOrCreateDatabase("Topics",Context.MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM topics",null)
            val topicNameIndex = cursor.getColumnIndex("topicName")
            val topicIdIndex = cursor.getColumnIndex("id")

                topicNameList.clear()
                topicIdList.clear()


             while (cursor.moveToNext()){
                 topicNameList.add(cursor.getString(topicNameIndex))
                 topicIdList.add(cursor.getInt(topicIdIndex))
             }


                listAdapter.notifyDataSetChanged()
                cursor.close()
            }


        }catch (e : Exception){
            e.printStackTrace()
        }



    }

}