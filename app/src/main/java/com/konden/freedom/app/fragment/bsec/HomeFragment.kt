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
import com.konden.freedom.app.adapter.AdapterLive
import com.konden.freedom.app.adapter.InfoAdapter
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.databinding.FragmentHomeBinding
import com.konden.freedom.app.model.InfoAllFreedom
import com.konden.freedom.app.model.LiveData



class HomeFragment : Fragment() , ListCall{
    private lateinit var FreedomArreyList: ArrayList<InfoAllFreedom>
    private lateinit var LiveArrayList: ArrayList<LiveData>
    private lateinit var binding : FragmentHomeBinding
    private var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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

        binding.rvNews.layoutManager = LinearLayoutManager(activity)
        binding.rvNews.setHasFixedSize(true)
        LiveArrayList = arrayListOf()



        getdata()
        GetLiveData()
    }

    private fun GetLiveData() {
        db.collection("live").orderBy("titel").get().addOnSuccessListener {
            if (!it.isEmpty)
                binding.lottieLoding.visibility = View.GONE
            binding.lottieLoding.cancelAnimation()
            for (data in it.documents) {
                val live: LiveData? =
                    data.toObject<LiveData>(LiveData::class.java)
                LiveArrayList.add(live!!)
            }

            binding.rvNews.adapter = AdapterLive(LiveArrayList,this)
        }
            .addOnFailureListener {
                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()

                Log.e("teeeeeeeeess", "teeeeeeeeess: ")
            }
    }


    private fun getdata() {
        db.collection("Notes").orderBy("name").get().addOnSuccessListener {
            if (!it.isEmpty){
                binding.lottieLoding.visibility = View.GONE

                for (data in it.documents) {
                    val user: InfoAllFreedom? =
                        data.toObject<InfoAllFreedom>(InfoAllFreedom::class.java)
                    FreedomArreyList.add(user!!)
                }
                binding.rv.adapter = InfoAdapter(FreedomArreyList)
            }
        }
            .addOnFailureListener {
                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()

                Log.e("teeeeeeeeess", "teeeeeeeeess: ")
            }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun call() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mod.gov.ps/ar/categoryPosts/3")))

    }
}