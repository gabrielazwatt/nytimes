package com.mostpopular.nytimes

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){

    lateinit var mInstance: BaseApplication
    lateinit var ress: Resources

    companion object {
        lateinit var instance: BaseApplication
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        ress = resources
        mInstance = this
    }

    fun getRes(): Resources {
        return ress
    }
    fun getInstance(): BaseApplication {
        return mInstance
    }
}