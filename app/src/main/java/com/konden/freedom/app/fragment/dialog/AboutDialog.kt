package com.konden.freedom.app.fragment.dialog

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
import com.konden.freedom.R
import com.konden.freedom.app.interfaces.ListFinish

class AboutDialog {
    fun dialog(context: Context , list:ListFinish) {
        SweetAlertDialog(
            context,
            SweetAlertDialog.WARNING_TYPE
        ).setTitleText(context.resources.getString(R.string.about_my_app))
            .setContentText("\n" + context.resources.getString(R.string.description_dialog) + "\n")
            .setConfirmButton(context.resources.getString(R.string.web_site_asre),
                object : OnSweetClickListener {
                    override fun onClick(sweetAlertDialog: SweetAlertDialog?) {
                        list.call()
//                        var intent: Intent =
//                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mod.gov.ps/ar"))
//                        startActivity(context, intent, null)
                    }
                })
            .setCancelText(context.resources.getString(R.string.ok))
            .show()
    }


    fun dialog_finish(context: Context, list:ListFinish) {
        SweetAlertDialog(
            context,
            SweetAlertDialog.ERROR_TYPE
        )
            .setContentText("\n" + context.resources.getString(R.string.text_finsh) + "\n")
            .setConfirmButton(context.resources.getString(R.string.finish),
                object : OnSweetClickListener {
                    override fun onClick(sweetAlertDialog: SweetAlertDialog?) {
                        list.call()
                    }
                })
            .setCancelText(context.resources.getString(R.string.cansel))
            .show()
    }
}