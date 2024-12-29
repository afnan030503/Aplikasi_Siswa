package com.example.aplikasisiswa

import android.app.Application
import com.example.aplikasisiswa.dependenciesinjection.AppContainer
import com.example.aplikasisiswa.dependenciesinjection.MahasiswaContainer

class MahasiswaApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container= MahasiswaContainer()
    }
}