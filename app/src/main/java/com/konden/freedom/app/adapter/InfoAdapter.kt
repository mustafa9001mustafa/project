package com.konden.freedom.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.konden.freedom.R
import com.konden.freedom.app.model.InfoAllFreedom

class InfoAdapter(private val infoList: ArrayList<InfoAllFreedom>) :
    RecyclerView.Adapter<InfoAdapter.MyViewHolderInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderInfo {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return MyViewHolderInfo(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderInfo, position: Int) {
        holder.name.text = infoList[position].name
        holder.number.text = infoList[position].number.toString()
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

     class MyViewHolderInfo(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name: TextView = itemview.findViewById(R.id.text_info_name)
        val number: TextView = itemview.findViewById(R.id.text_info_number)
    }
}