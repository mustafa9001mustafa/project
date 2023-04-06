package com.konden.freedom.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.model.AlsraData
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ItemDangerBinding

class AdapterDanger(var dangerList: ArrayList<AlsraData> , var call:ListCall) :
    RecyclerView.Adapter<AdapterDanger.ViewHOlderDanger>() {

    inner class ViewHOlderDanger(var v: ItemDangerBinding) : RecyclerView.ViewHolder(v.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHOlderDanger {
        val binding = ItemDangerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHOlderDanger(binding)
    }

    override fun getItemCount(): Int {
        return dangerList.size
    }

    override fun onBindViewHolder(holder: ViewHOlderDanger, position: Int) {
        val data : AlsraData = dangerList[position]
        holder.v.nameFree.text = data.titel
        holder.v.contreFree.text = data.description
        holder.v.root.setOnClickListener(View.OnClickListener {
            call.call(data.Link.toString())
        })
    }
}