package com.konden.freedom.app.fragment.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.konden.freedom.R
import com.konden.freedom.databinding.FragmentEnterPasswordBinding


class EnterPasswordFragment : DialogFragment() {

    private var style: Int = DialogFragment.STYLE_NO_TITLE
    private var themes: Int = R.style.MyDialog
    private var _binding: FragmentEnterPasswordBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            setStyle(style, themes)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterPasswordBinding.inflate(inflater, container, false)
        AllFun()
        return binding.root

    }

    private fun AllFun() {

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