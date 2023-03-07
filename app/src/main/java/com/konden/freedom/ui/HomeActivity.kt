package com.konden.freedom.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.adapter.InfoAdapter
import com.konden.freedom.databinding.ActivityHomeBinding
import com.konden.freedom.model.InfoAllFreedom


class HomeActivity : AppCompatActivity() {
    private lateinit var FreedomArreyList: ArrayList<InfoAllFreedom>
    var db = Firebase.firestore
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        inshlase()

    }

    private fun inshlase() {
        binding.rv.layoutManager =
            GridLayoutManager(this@HomeActivity, 3, GridLayoutManager.VERTICAL, false)
        binding.rv.setHasFixedSize(true)
        FreedomArreyList = arrayListOf()
        teeeeeeeeess()
//        tester()
    }

    private fun teeeeeeeeess() {
        db.collection("Users").get().addOnSuccessListener {
            if (!it.isEmpty)
                for (data in it.documents) {
                    val user: InfoAllFreedom? =
                        data.toObject<InfoAllFreedom>(InfoAllFreedom::class.java)
                    FreedomArreyList.add(user!!)

                }
            binding.rv.adapter = InfoAdapter(FreedomArreyList)
        }
            .addOnFailureListener {
                Toast.makeText(this@HomeActivity, "error", Toast.LENGTH_SHORT).show()
            }
    }


    private fun tester() {

        db.collection("info freedom").get().addOnSuccessListener {
            if (!it.isEmpty) {

                binding.progressCircular.visibility = View.GONE

                for (data in it.documents) {
                    Log.e("TAG", "${data.id}=>${data.data}")
                    val info: InfoAllFreedom? = data.toObject(InfoAllFreedom::class.java)
                    if (info != null) {
                        FreedomArreyList.add(info)
                    }
                }
//                    binding.rv.adapter = infoAdapter
//                    infoAdapter.notifyDataSetChanged()

            }
        }
            .addOnFailureListener {
                Log.e("TAGRRRR", "onEvent: error")
                Toast.makeText(this@HomeActivity, "", Toast.LENGTH_SHORT).show()
            }

    }
}

