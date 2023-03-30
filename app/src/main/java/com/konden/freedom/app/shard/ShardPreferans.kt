package com.konden.freedom.app.shard

import android.app.Activity
import android.content.SharedPreferences
import com.konden.readandcuttext.appcontroller.AppController

class ShardPreferans private constructor() {

    companion object {
        private val sharePref = ShardPreferans()
        private lateinit var sharedPreferences: SharedPreferences

        private val Login = "Login"
        private val Gust = "Gust"
        private val Name = "name"
        private val DataAsra = "dataAsra"
        private val DataFreedom = "dataFreedom"
        private val Number = "Number"

        fun getInstance(): ShardPreferans {
            if (!Companion::sharedPreferences.isInitialized) {
                synchronized(ShardPreferans::class.java) {
                    if (!Companion::sharedPreferences.isInitialized) { sharedPreferences =
                        AppController.instance.getSharedPreferences(AppController.instance.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    var statesLogin: Boolean = true
        get() = sharedPreferences.getBoolean(Login, field)

    fun saveLogin(login: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Login, login)
            .apply()
    }


    var GustLogin: Boolean = false
        get() = sharedPreferences.getBoolean(Gust, field)

    fun GustLogin(gust: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Gust, gust)
            .apply()
    }


    val getName: String? = "غير معروف الإسم"
        get() = sharedPreferences.getString(Name, field)

    fun saveName(str: String) {
        sharedPreferences.edit()
            .putString(Name, str)
            .apply()
    }


    val getDataAser: String? = "غير معروف مكان متى تم أسره"
        get() = sharedPreferences.getString(DataAsra, field)

    fun saveDataAser(str: String) {
        sharedPreferences.edit()
            .putString(DataAsra, str)
            .apply()
    }


    val getDataFreedom: String? = "غير معروف متى موعد التحرير"
        get() = sharedPreferences.getString(DataFreedom, field)

    fun saveDataFreedom(str: String) {
        sharedPreferences.edit()
            .putString(DataFreedom, str)
            .apply()
    }


    val getNumber: String? = "غير معروف الرقم"
        get() = sharedPreferences.getString(Number, field)

    fun saveNumber(str: String) {
        sharedPreferences.edit()
            .putString(Number, str)
            .apply()
    }


    fun isFirstTimeOther(): Boolean {
        val ranBefore = sharedPreferences.getBoolean("RanBefore", false)
        if (!ranBefore) {
            sharedPreferences.edit().putBoolean("RanBefore", true)
                .apply()
        }
        return !ranBefore
    }
    fun clear() {
        sharedPreferences.edit().remove(Login).remove(Name).remove(DataAsra).remove(DataFreedom).remove(Number).remove(Gust).apply()

    }

}