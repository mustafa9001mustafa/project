package com.konden.freedom.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.model.AlsraData
import com.konden.freedom.databinding.ItemLiveBinding

class AdapterLive(var LiveList: ArrayList<AlsraData>, var call:ListCall) :
    RecyclerView.Adapter<AdapterLive.ViewHolderLive>() {

    inner class ViewHolderLive(var v: ItemLiveBinding) : RecyclerView.ViewHolder(v.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLive {
        val binding = ItemLiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderLive(binding)

    }

    override fun getItemCount(): Int {
        return LiveList.size
    }

    override fun onBindViewHolder(holder: ViewHolderLive, position: Int) {
        val live : AlsraData = LiveList[position]
        holder.v.nameFree.text = live.titel
        holder.v.contreFree.text = live.description
        holder.v.root.setOnClickListener(View.OnClickListener {
            call.call(live.Link.toString())
        })
    }
}