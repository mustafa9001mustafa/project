package com.konden.freedom.app.fragment.bsec

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.app.ui.LoginActivity
import com.konden.freedom.databinding.FragmentProfileBinding
import org.checkerframework.framework.qual.InvisibleQualifier


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

        LOGOUT()
        LOGIN_TO()


        return binding.root
    }

    private fun LOGIN_TO() {
        binding.login.setOnClickListener(View.OnClickListener {

            startActivity(Intent(activity, LoginActivity::class.java))
            ShardPreferans.getInstance().clear()
            ShardPreferans.getInstance().saveLogin(false)
            ShardPreferans.getInstance().GustLogin(false)
            activity?.finish()

        })
    }

    private fun InVisibleData() {
        binding.cardView.visibility = View.GONE
        binding.logout.visibility = View.GONE

    }


    private fun LOGOUT() {
        binding.logout.setOnClickListener(View.OnClickListener {
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

}