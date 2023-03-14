package com.konden.readandcuttext.appcontroller

import android.app.Application

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
    fun getInstance(): AppController? {
        return instance
    }

    companion object {
        lateinit var instance: AppController
    }
}