package com.konden.freedom.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.model.DangerData
import com.konden.freedom.databinding.ItemDangerBinding

class AdapterDanger(var dangerList: ArrayList<DangerData> , var call:ListCall) :
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
        val danger : DangerData = dangerList[position]
        holder.v.nameFree.text = danger.titel
        holder.v.contreFree.text = danger.description
        holder.v.root.setOnClickListener(View.OnClickListener {
            call.call()
        })
    }
}