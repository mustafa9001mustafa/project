package com.konden.freedom.app.fragment.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.R
import com.konden.freedom.app.interfaces.ListCallChoose
import com.konden.freedom.app.interfaces.ListFinish
import com.konden.freedom.app.interfaces.ListenerCallLanguage
import com.konden.freedom.app.interfaces.ListenerCallPassword
import com.konden.freedom.app.ui.AdminActivity
import com.konden.freedom.databinding.FragmentEnterPasswordBinding


class EnterPasswordFragment : DialogFragment() {

    private var style: Int = DialogFragment.STYLE_NO_TITLE
    private var themes: Int = R.style.MyDialog
    private var _binding: FragmentEnterPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: ListenerCallPassword



    var db = Firebase.firestore


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerCallPassword)
            listener = context
        else
            throw ClassCastException(requireContext().toString() + " must implement OnTimeListener.")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            setStyle(style, themes)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterPasswordBinding.inflate(inflater, container, false)
        AllFun()
        return binding.root

    }

    private fun AllFun() {
        binding.btnGo.setOnClickListener(View.OnClickListener {
            GetData(binding.editPassword.text.toString())
        })
    }


    private fun GetData(password: String) {
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val Password = data.get("Password").toString()

                    if (Password.equals(password)) {
                        listener.open()
                        startActivity(Intent(activity, AdminActivity::class.java))


                    } else {
                        listener.closed()
                        binding.btnGo.visibility = View.GONE
                        binding.editPassword.visibility = View.GONE
                        binding.textNotNecessary.text = " يرجى المحاولة في ما بعد"
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EnterPasswordFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.width = FrameLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes?.height = FrameLayout.LayoutParams.WRAP_CONTENT
    }
}