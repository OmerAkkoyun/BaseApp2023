package com.omerakkoyun.mybaseapp1.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CryptoApp: Application() {

    companion object{

        private lateinit var instance : CryptoApp

        fun getAppContext(): Context = instance.applicationContext

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}

/// context'e ihtiyac duyan diger siniflar buradan context' erisebilir. ///