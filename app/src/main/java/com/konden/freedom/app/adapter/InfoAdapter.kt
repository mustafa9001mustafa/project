package com.konden.freedom.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.konden.freedom.R
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.model.InfoAllFreedom
import com.konden.freedom.databinding.ItemInfoBinding
import com.konden.readandcuttext.appcontroller.AppController

class InfoAdapter(private val infoList: ArrayList<InfoAllFreedom>, var call: ListCall) :
    RecyclerView.Adapter<InfoAdapter.MyViewHolderInfo>() {

//    var x : Int = 1

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


        holder.v.conRoot.animation = anim()

        holder.v.conRoot.setOnClickListener {
            call.call(infoList[position].number.toString() + "وعددهم " + infoList[position].name + "إحصائيات ال ")


//                if (x <= 9){
//                x++
//                Log.e("TAG", "onBindViewHolder: 12534577465241324354  "+x )
//                holder.v.textInfoName.text = infoList[position].name
//                holder.v.textInfoNumber.text = infoList[position].number.toString()
//                holder.v.textInfoName.setAnimation(anim())
//                holder.v.textInfoNumber.setAnimation(anim())
//            }else
        }

    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    private fun anim(): Animation? {
        return AnimationUtils.loadAnimation(
            AppController.instance,
            R.anim.smalltobig
        )
    }

//     class MyViewHolderInfo(item_view: View) : RecyclerView.ViewHolder(item_view) {
//        val name: TextView = item_view.findViewById(R.id.text_info_name)
//        val number: TextView = item_view.findViewById(R.id.text_info_number)
//    }
}

