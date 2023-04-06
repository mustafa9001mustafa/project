package com.konden.readandcuttext.appcontroller

import android.app.Application
import com.yariksoffice.lingver.Lingver

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Lingver.init(this)
    }

    companion object {
        lateinit var instance: AppController
    }
}