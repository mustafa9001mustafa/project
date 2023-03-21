package com.konden.freedom.app.fragment.bsec

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.nameFree.text =
            binding.nameFree.text.toString() + ShardPreferans.getInstance().getName
        binding.dataAser.text =
            binding.dataAser.text.toString() + ShardPreferans.getInstance().getDataAser
        binding.dataFreedom.text =
            binding.dataFreedom.text.toString() + ShardPreferans.getInstance().getDataFreedom
        binding.numberDecimal.text =
            binding.numberDecimal.text.toString() + ShardPreferans.getInstance().getNumber


        binding.logout.setOnClickListener(View.OnClickListener {
            ShardPreferans.getInstance().saveLogin(false)
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}