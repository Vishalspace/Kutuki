package com.kutuki

import android.app.Application
import com.kutuki.di.AppComponent
import com.kutuki.di.ContextModule
import com.kutuki.di.DaggerAppComponent
import com.kutuki.di.NetModule

class App : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .netModule(NetModule())
            .build()
    }

}