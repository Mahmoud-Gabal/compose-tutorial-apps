package com.example.mybookshelf.Application

import android.app.Application
import com.example.mybookshelf.RepoAndContainers.DefaultContainer
import com.example.mybookshelf.RepoAndContainers.appContainer

class MyApplication : Application(){
    lateinit var container : appContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}