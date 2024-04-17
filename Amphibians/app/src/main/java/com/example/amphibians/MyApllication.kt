package com.example.amphibians

import android.app.Application

class MyApllication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}