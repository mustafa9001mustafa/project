package com.konden.freedom.app.ui

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

import cn.pedant.SweetAlert.SweetAlertDialog
import com.konden.freedom.R
import com.konden.freedom.app.fragment.dialog.AboutDialog
import com.konden.freedom.app.fragment.dialog.EnterPasswordFragment
import com.konden.freedom.app.fragment.dialog.LanguagesFragment
import com.konden.freedom.app.interfaces.ListCallChoose
import com.konden.freedom.app.interfaces.ListFinish
import com.konden.freedom.app.interfaces.ListenerCallLanguage
import com.konden.freedom.app.interfaces.ListenerCallPassword
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ActivitySettingBinding
import com.konden.readandcuttext.appcontroller.AppController
import com.yariksoffice.lingver.Lingver
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class SettingActivity : AppCompatActivity(), ListenerCallLanguage, ListFinish, ListenerCallPassword {


    private lateinit var binding: ActivitySettingBinding

    private lateinit var dialog: LanguagesFragment
    private lateinit var dialog_password: EnterPasswordFragment
    val about_dialog: AboutDialog = AboutDialog()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ShardPreferans.getInstance().GetSize) {
            binding.btnSize.setText(resources.getText(R.string.mid))
            size_mid()
        } else {
            binding.btnSize.setText(resources.getText(R.string.larg))
            size_larg()
        }
    }

    override fun onStart() {
        super.onStart()
        All_Fun()
    }

    private fun All_Fun() {
        Languages()
        Size_Text()
        About_app()
        Back_Btn()
        Btn_reset()
        Admin()
    }

    private fun Admin() {
        binding.cardAdmin.setOnClickListener(View.OnClickListener {
            dialog_password =
                EnterPasswordFragment.newInstance()//show dialog description according to user type
            this@SettingActivity.supportFragmentManager.beginTransaction()
                .let { it1 ->
                    dialog_password.show(it1, LanguagesFragment::class.java.name)
                }
        })
    }

    private fun Btn_reset() {
        binding.resetBtn.setOnClickListener(View.OnClickListener {





            SweetAlertDialog(this@SettingActivity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("إنتبه")
                .setContentText("سيقوم التطبيق بإعادة التشغيل مع حذف جميع البينات والإعدادات المحفوظة داخل التطبيق")
                .setConfirmText(resources.getString(R.string.cansel))
                .setCancelButton("إعادة ضبط") {
                    ShardPreferans.getInstance().clear()
                    MotionToast.createColorToast(
                        this@SettingActivity,
                        "جاري إعادة ضبط التطبيق",
                        "إنتظر بضع ثواني لإتمام إعداة الضبط بنجاح",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        Typeface.MONOSPACE
                    )



                    startActivity(Intent(this@SettingActivity, SplachScreen::class.java))
                    finish()

                }
                .show()
        })
    }

    private fun Back_Btn() {
        binding.backIconS.setOnClickListener(View.OnClickListener {
            startActivity(Intent(Intent(this@SettingActivity, HomeActivity::class.java)))
        })
    }


    private fun About_app() {
        binding.cardAbout.setOnClickListener(View.OnClickListener {
            about_dialog.dialog(this@SettingActivity, this)


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

    private fun Size_Text() {
        binding.btnSize.setOnClickListener(View.OnClickListener {
            if (binding.btnSize.text == resources.getText(R.string.mid)) {
                binding.btnSize.text = resources.getText(R.string.larg)
                ShardPreferans.getInstance().saveSize(false)
                size_larg()
            } else {
                binding.btnSize.text = resources.getText(R.string.mid)
                ShardPreferans.getInstance().saveSize(true)
                size_mid()

            }
        })
    }

    private fun size_mid() {
        binding.about.textSize = 16f
        binding.btnSize.textSize = 16f
        binding.lan.textSize = 16f
        binding.sizeText.textSize = 16f
        binding.reset.textSize = 16f
        binding.resetBtn.textSize = 16f
        binding.tvAdmin.textSize = 16f
    }

    private fun size_larg() {
        binding.about.textSize = 20f
        binding.btnSize.textSize = 20f
        binding.lan.textSize = 20f
        binding.sizeText.textSize = 20f
        binding.reset.textSize = 20f
        binding.resetBtn.textSize = 20f
        binding.tvAdmin.textSize = 20f
    }


    override fun lis(lan: Boolean) {
        super.lis(lan)
        if (lan == true)
            Lingver.getInstance().setLocale(AppController.instance, "ar")
        else
            Lingver.getInstance().setLocale(AppController.instance, "en")
        dialog.dismiss()
    }

    override fun open() {

        MotionToast.createColorToast(this@SettingActivity, "تسجيل الدخول", "تم تسجيل الدخول للوحة التحكم",
            MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
    }

    override fun closed() {
        MotionToast.createColorToast(
            this@SettingActivity, "حدث خطأ ما", "كلمة المرور غير صحيحة",
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD
        )
    }

    override fun close() {
        dialog.dismiss()
    }

    override fun onBackPressed() {
        startActivity(Intent(Intent(this@SettingActivity, HomeActivity::class.java)))
        finish()
    }

    override fun call() {
        val intent: Intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mod.gov.ps/ar"))
        startActivity(intent, null)
    }
}