package com.konden.freedom.app.fragment.bsec

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.app.ui.LoginActivity
import com.konden.freedom.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    //    @SuppressLint("SetTextI18n")
    //
    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        if (ShardPreferans.getInstance().GustLogin == true)
            InVisibleData()
        else
            SHOW_DATA()


        AllFun()

        return binding.root
    }


    private fun AllFun() {
        LOGOUT()
        LOGIN_TO()
        SizeALlText()
    }


    private fun Auth_Just_Now(data_name: String) {
        val db = Firebase.firestore
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val auth = data.get(data_name).toString().toInt()
                    var sum: Int
                    if (data_name.equals("Logout"))
                        sum = auth + 1
                    else
                        sum = auth - 1

                    val data1 = mapOf<String, Int>(data_name to sum)
                    val id: String = "W0wd10pOXrM94MCUpnFQ"
                    db.collection("Admin").document(id).update(data1).addOnSuccessListener {
                        Log.d("TAG", "Guest_Just_Now: ")
                    }.addOnFailureListener {
                        Log.e("TAG", "Guest_Just_Now: ")
                    }
                }
            }

        }
    }

    private fun LOGIN_TO() {
        binding.login.setOnClickListener(View.OnClickListener {
            Auth_Just_Now("GuestNow")
            startActivity(Intent(activity, LoginActivity::class.java))
            ShardPreferans.getInstance().saveLogin(false)
            ShardPreferans.getInstance().GustLogin(false)
            ShardPreferans.getInstance().remove()
            activity?.finish()

        })
    }

    private fun InVisibleData() {
        binding.cardView.visibility = View.GONE
        binding.logout.visibility = View.GONE

    }


    private fun LOGOUT() {
        binding.logout.setOnClickListener(View.OnClickListener {
            Auth_Just_Now("Login")
            Auth_Just_Now("Logout")
            ShardPreferans.getInstance().clear()
            ShardPreferans.getInstance().saveLogin(false)
            ShardPreferans.getInstance().GustLogin(false)
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        })
    }

    private fun SHOW_DATA() {
        binding.nameFree.text =
            binding.nameFree.text.toString() + ShardPreferans.getInstance().getName
        binding.dataAser.text =
            binding.dataAser.text.toString() + ShardPreferans.getInstance().getDataAser
        binding.dataFreedom.text =
            binding.dataFreedom.text.toString() + ShardPreferans.getInstance().getDataFreedom
        binding.numberDecimal.text =
            binding.numberDecimal.text.toString() + ShardPreferans.getInstance().getNumber

        binding.login.visibility = View.GONE

    }

    private fun SizeALlText() {
        if (!ShardPreferans.getInstance().GetSize)
            size_larg()
        else
            size_mid()
    }

    private fun size_mid() {
        binding.logout.textSize = 16f
        binding.textNotNecessary.textSize = 16f
        binding.login.textSize = 16f
        binding.dataAser.textSize = 16f
        binding.numberDecimal.textSize = 16f
        binding.dataFreedom.textSize = 16f
        binding.textView.textSize = 16f
        binding.nameFree.textSize = 16f
    }

    private fun size_larg() {
        binding.logout.textSize = 20f
        binding.textNotNecessary.textSize = 20f
        binding.login.textSize = 20f
        binding.dataAser.textSize = 20f
        binding.numberDecimal.textSize = 20f
        binding.dataFreedom.textSize = 20f
        binding.textView.textSize = 20f
        binding.nameFree.textSize = 20f
    }

}