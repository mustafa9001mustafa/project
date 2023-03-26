package com.konden.readandcuttext.appcontroller

import android.app.Application

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        lateinit var instance: AppController
    }
}