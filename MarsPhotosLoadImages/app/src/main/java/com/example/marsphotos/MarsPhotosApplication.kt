package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var Container : AppContainer
    override fun onCreate() {
        super.onCreate()
        Container = DefaultAppContainer()
    }
}