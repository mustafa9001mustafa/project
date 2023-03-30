package com.konden.freedom.app.fragment.bsec

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.R
import com.konden.freedom.app.adapter.AdapterLive
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.model.AlsraData
import com.konden.freedom.databinding.FragmentHomeBinding
import com.konden.freedom.databinding.FragmentLiveBinding


class LiveFragment : Fragment()  , ListCall {
    private lateinit var LiveArrayList: ArrayList<AlsraData>
    private lateinit var binding : FragmentLiveBinding
    private var db = Firebase.firestore

    companion object {
        @JvmStatic
        fun newInstance() =
            LiveFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater,container,false)
        db = FirebaseFirestore.getInstance()
        inshlase()
        return binding.root
    }


    private fun inshlase() {
        binding.rvNews.layoutManager = LinearLayoutManager(activity)
        binding.rvNews.setHasFixedSize(true)
        LiveArrayList = arrayListOf()
        GetLiveData()
    }

    private fun GetLiveData() {
        db.collection("live").orderBy("titel").get().addOnSuccessListener {
            if (!it.isEmpty)
                binding.lottieLoding.visibility = View.GONE
            binding.lottieLoding.cancelAnimation()
            for (data in it.documents) {
                val live: AlsraData? = data.toObject<AlsraData>(AlsraData::class.java)
                LiveArrayList.add(live!!)
            }

            binding.rvNews.adapter = AdapterLive(LiveArrayList,this)
        }
            .addOnFailureListener {
                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()

                Log.e("teeeeeeeeess", "teeeeeeeeess: ")
            }
    }

    override fun call(link: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))

    }
}