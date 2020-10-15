package com.example.pm2

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class DataAdapter(dataLists: MutableList<DataList>, listener: OnItemClickListener, context: Context) : RecyclerView.Adapter<ListHolder>() {
    private val Datalists = dataLists
    private val listeners = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false)
        return ListHolder(inflater)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder?.bindContent(Datalists[position], listeners, position)
    }

    override fun getItemCount(): Int = Datalists.size


}