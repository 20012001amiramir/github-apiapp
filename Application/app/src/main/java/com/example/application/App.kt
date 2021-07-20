package com.example.application

import android.app.Application
import com.example.application.data.di.CoreComponent
import com.example.application.data.di.DaggerCoreComponent
import com.example.application.di.AppComponent
import com.example.application.di.DaggerAppComponent

class App : Application(){
    private val coreComponent : CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}
