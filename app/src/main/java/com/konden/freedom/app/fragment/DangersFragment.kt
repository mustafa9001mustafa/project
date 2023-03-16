package com.konden.freedom.app.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.app.adapter.AdapterDanger
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.model.DangerData
import com.konden.freedom.databinding.FragmentDangersBinding
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class DangersFragment : Fragment(), ListCall {

    private lateinit var DangerDataArrayList: ArrayList<DangerData>
    private lateinit var binding: FragmentDangersBinding
    var dbData = Firebase.firestore
    private lateinit var list_call: ListCall
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDangersBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = LinearLayoutManager(activity)
        binding.rv.setHasFixedSize(true)
        DangerDataArrayList = arrayListOf()

//        getdata()
        return binding.root
    }

    private fun getdata() {
        dbData.collection("DangerCard").orderBy("titel").get().addOnSuccessListener {
            if (!it.isEmpty)
                binding.lottieLoding.visibility = View.GONE
            binding.lottieLoding.cancelAnimation()

            for (data in it.documents) {
                val danger_data: DangerData? =
                    data.toObject<DangerData>(DangerData::class.java)

                DangerDataArrayList.add(danger_data!!)
            }
            binding.rv.adapter = AdapterDanger(DangerDataArrayList, this)
        }
            .addOnFailureListener {
                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()

                Log.e("teeeeeeeeess", "teeeeeeeeess: ")
            }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DangersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun call() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mod.gov.ps/ar/categoryPosts/23")))
    }
}