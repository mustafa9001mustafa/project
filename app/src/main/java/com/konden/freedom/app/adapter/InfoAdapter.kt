package com.konden.freedom.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.konden.freedom.R
import com.konden.freedom.app.model.InfoAllFreedom
import com.konden.freedom.databinding.ItemInfoBinding
import com.konden.readandcuttext.appcontroller.AppController

class InfoAdapter(private val infoList: ArrayList<InfoAllFreedom>) :
    RecyclerView.Adapter<InfoAdapter.MyViewHolderInfo>() {
    inner class MyViewHolderInfo(var v: ItemInfoBinding) : RecyclerView.ViewHolder(v.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderInfo {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolderInfo(binding)
//
//        val itemView =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
//        return MyViewHolderInfo(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderInfo, position: Int) {
        holder.v.textInfoName.text = infoList[position].name
        holder.v.textInfoNumber.text = infoList[position].number.toString()

        holder.v.conRoot.animation = AnimationUtils.loadAnimation(
            AppController.instance,
            R.anim.smalltobig
        )


//todo
//        holder.v.conRoot.setOnClickListener(View.OnClickListener {
//            val animation = AnimationUtils.loadAnimation(AppController.instance, R.anim.smalltobig)
//            Log.e("TAG", "onBindViewHolder: " )
//            holder.v.conRoot.animation = animation
//
//        })


    }

    override fun getItemCount(): Int {
        return infoList.size
    }

//     class MyViewHolderInfo(item_view: View) : RecyclerView.ViewHolder(item_view) {
//        val name: TextView = item_view.findViewById(R.id.text_info_name)
//        val number: TextView = item_view.findViewById(R.id.text_info_number)
//    }
}