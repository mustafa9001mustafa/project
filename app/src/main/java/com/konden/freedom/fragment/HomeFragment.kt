package com.konden.freedom.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.R
import com.konden.freedom.adapter.InfoAdapter
import com.konden.freedom.databinding.FragmentHomeBinding
import com.konden.freedom.model.InfoAllFreedom

private lateinit var FreedomArreyList: ArrayList<InfoAllFreedom>
private lateinit var binding : FragmentHomeBinding
var db = Firebase.firestore
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        db = FirebaseFirestore.getInstance()
        inshlase()
        return binding.root
    }

    private fun inshlase() {
        binding.rv.layoutManager =
            GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.rv.setHasFixedSize(true)
        FreedomArreyList = arrayListOf()
        getdata()
    }


    private fun getdata() {
        db.collection("Notes").orderBy("name").get().addOnSuccessListener {
            if (!it.isEmpty)
                binding.lottieLoding.visibility = View.GONE
            binding.lottieLoding.cancelAnimation()
            for (data in it.documents) {
                val user: InfoAllFreedom? =
                    data.toObject<InfoAllFreedom>(InfoAllFreedom::class.java)
                FreedomArreyList.add(user!!)
            }
            binding.rv.adapter = InfoAdapter(FreedomArreyList)
        }
            .addOnFailureListener {
                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()

                Log.e("teeeeeeeeess", "teeeeeeeeess: ")
            }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}