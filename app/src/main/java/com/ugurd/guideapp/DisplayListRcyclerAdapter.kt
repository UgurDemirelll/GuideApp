package com.ugurd.guideapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyler_display_row.view.*

class DisplayListRcyclerAdapter(val topicList : ArrayList<String>, val idList : ArrayList<Int>) : RecyclerView.Adapter<DisplayListRcyclerAdapter.TopicHolder>(){

    class TopicHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyler_display_row,parent,false)
        return TopicHolder(view)
    }

    override fun onBindViewHolder(holder: TopicHolder, position: Int) {
        holder.itemView.display_recycler_row_text.text = topicList[position]
    }

    override fun getItemCount(): Int {
            return topicList.size
    }

}