package com.konden.freedom.app.fragment.bsec

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.flexbox.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.R
import com.konden.freedom.app.adapter.InfoAdapter
import com.konden.freedom.app.fragment.dialog.AboutDialog
import com.konden.freedom.app.interfaces.ListCall
import com.konden.freedom.app.interfaces.ListCallChoose
import com.konden.freedom.app.interfaces.ListFinish
import com.konden.freedom.app.model.InfoAllFreedom
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.app.ui.SettingActivity
import com.konden.freedom.databinding.FragmentHomeBinding
import com.konden.readandcuttext.appcontroller.AppController
import com.konden.storonline.animations.Animations


class HomeFragment : Fragment(), ListCall ,ListFinish{
    private lateinit var FreedomArreyList: ArrayList<InfoAllFreedom>
    private lateinit var binding: FragmentHomeBinding
    private var db = Firebase.firestore
    private lateinit var call_choose: ListCallChoose
    private val anim: Animations = Animations()
    val about_dialog: AboutDialog = AboutDialog()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onAttach(context: Context) {
            super.onAttach(context)
        if (context is ListCallChoose) {
            call_choose = context
        } else {
            throw ClassCastException(requireContext().toString() + " must implement OnTimeSettedListener.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()
        SizeALlText()
        AllMethod()
        return binding.root
    }


    private fun AllMethod() {
        inshlase()
        OnClickChoose()
        Siwp()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun Siwp() {
        binding.lottieHide.setOnTouchListener(OnTouchListener { _, motionEvent ->
            val event = motionEvent.actionMasked
            when (event) {
                MotionEvent.ACTION_UP -> {
                    if (binding.lottieHide.rotation == 0f) {
                        binding.constraintLayout.animation = anim.a1_Up(activity)
                        binding.lottieHide.animation = anim.a1_Up(activity)
                        binding.constraintLayout.visibility = View.VISIBLE
                        binding.lottieHide.rotation = 180f
                        binding.constraintNiy.visibility = View.GONE
                    } else {
                        binding.constraintLayout.visibility = View.GONE
                        binding.constraintNiy.visibility = View.VISIBLE

                        binding.lottieHide.rotation = 0f
                        binding.constraintLayout.animation = anim.a2_Down(activity)
                    }
                }
            }
            true
        })
    }

    private fun OnClickChoose() {
        binding.live.setOnClickListener(View.OnClickListener {
            call_choose.call(2)
        })
        binding.danger.setOnClickListener(View.OnClickListener {
            call_choose.call(3)

        })
        binding.profile.setOnClickListener(View.OnClickListener {
            call_choose.call(4)

        })
        binding.webSite.setOnClickListener(View.OnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mod.gov.ps/ar")))
        })


        binding.about.setOnClickListener(View.OnClickListener {
            about_dialog.dialog(requireContext(),this)
        })

        binding.setting.setOnClickListener(View.OnClickListener {
            startActivity(Intent(activity, SettingActivity::class.java))
        })
    }

    private fun inshlase() {

        val layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }

        binding.rv.layoutManager = layoutManager
        binding.rv.setHasFixedSize(true)
        FreedomArreyList = arrayListOf()

        getdata()
    }

    private fun getdata() {
        db.collection("Notes").orderBy("name").get().addOnSuccessListener {
            if (!it.isEmpty) {
                binding.lottieLoding.visibility = View.GONE

                for (data in it.documents) {
                    val user: InfoAllFreedom? =
                        data.toObject<InfoAllFreedom>(InfoAllFreedom::class.java)
                    FreedomArreyList.add(user!!)
                }
                binding.rv.adapter = InfoAdapter(FreedomArreyList)
            }
        }.addOnFailureListener {
            Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()

            Log.e("teeeeeeeeess", "teeeeeeeeess: ")
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun call(link: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    private fun SizeALlText() {
        if (!ShardPreferans.getInstance().GetSize)
            size_larg()
        else
            size_mid()
    }

    private fun size_mid() {
        binding.about.textSize = 16f
        binding.textNotNecessary.textSize = 16f
        binding.live.textSize = 16f
        binding.webSite.textSize = 16f
        binding.setting.textSize = 16f
        binding.profile.textSize = 16f
        binding.danger.textSize = 16f
        binding.notNecessary.textSize = 16f
    }

    private fun size_larg() {
        binding.about.textSize = 18f
        binding.textNotNecessary.textSize = 18f
        binding.live.textSize = 18f
        binding.webSite.textSize = 17f
        binding.setting.textSize = 18f
        binding.profile.textSize = 18f
        binding.danger.textSize = 18f
        binding.notNecessary.textSize = 18f
    }

    override fun call() {
        var intent: Intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mod.gov.ps/ar"))
        startActivity( intent, null)
    }
}