package com.konden.freedom.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.konden.freedom.R
import com.konden.freedom.app.fragment.dialog.LanguagesFragment
import com.konden.freedom.app.interfaces.ListenerCallLanguage
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ActivitySettingBinding
import com.konden.readandcuttext.appcontroller.AppController
import com.yariksoffice.lingver.Lingver

class SettingActivity : AppCompatActivity(), ListenerCallLanguage {


    private lateinit var binding: ActivitySettingBinding

    private lateinit var dialog: LanguagesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (!ShardPreferans.getInstance().GetSize) {
            binding.btnSize.setText(resources.getText(R.string.mid))
            size_mid()
        } else {
            binding.btnSize.setText(resources.getText(R.string.larg))
            size_larg()
        }
    }

    override fun onStart() {
        super.onStart()
        all_Method()
    }


    private fun all_Method() {
        Languages()
        Size_Text()
        About_app()
        Back_Btn()
    }

    private fun Back_Btn() {
        binding.backIconS.setOnClickListener(View.OnClickListener {
            startActivity(Intent(Intent(this@SettingActivity, HomeActivity::class.java)))
        })
    }

    private fun Size_Text() {
        binding.btnSize.setOnClickListener(View.OnClickListener {
            if (binding.btnSize.text == resources.getText(R.string.mid)) {
                binding.btnSize.text = resources.getText(R.string.larg)
                ShardPreferans.getInstance().saveSize(true)
                size_larg()

            } else {
                binding.btnSize.text = resources.getText(R.string.mid)
                ShardPreferans.getInstance().saveSize(false)
                size_mid()

            }

        })

    }

    private fun About_app() {
        binding.cardAbout.setOnClickListener(View.OnClickListener {
            SweetAlertDialog(
                this@SettingActivity,
                SweetAlertDialog.WARNING_TYPE
            ).setTitleText(resources.getString(R.string.about_my_app))
                .setContentText(resources.getString(R.string.description_dialog))
                .setConfirmText(resources.getString(R.string.ok))
                .show()

        })
    }


    private fun Languages() {
        binding.cardLan.setOnClickListener {
            dialog = LanguagesFragment.newInstance(
                getString(R.string.choosethelanguage),
                getString(R.string.Arabic),
                getString(R.string.english)
            )//show dialog description according to user type
            this@SettingActivity.supportFragmentManager.beginTransaction()
                .let { it1 ->
                    dialog.show(it1, LanguagesFragment::class.java.name)
                }
        }
    }

    private fun size_mid() {
        binding.about.textSize = 16f
        binding.btnSize.textSize = 16f
        binding.lan.textSize = 16f
        binding.sizeText.textSize = 16f
    }

    private fun size_larg() {
        binding.about.textSize = 20f
        binding.btnSize.textSize = 20f
        binding.lan.textSize = 20f
        binding.sizeText.textSize = 20f
    }


    override fun lis(lan: Boolean) {
        super.lis(lan)
        if (lan == true)
            Lingver.getInstance().setLocale(AppController.instance, "ar")
        else
            Lingver.getInstance().setLocale(AppController.instance, "en")
        dialog.dismiss()
    }

    override fun close() {
        dialog.dismiss()
    }

    override fun onBackPressed() {
        startActivity(Intent(Intent(this@SettingActivity, HomeActivity::class.java)))
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}