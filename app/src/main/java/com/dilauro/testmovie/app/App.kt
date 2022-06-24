package com.dilauro.testmovie.app

import android.app.Application
import com.dilauro.testmovie.di.ApiServiceComponent
import com.dilauro.testmovie.di.ApiServiceModule
import com.dilauro.testmovie.di.DaggerApiServiceComponent

class App : Application() {

    private lateinit var apiServiceComponent: ApiServiceComponent

    override fun onCreate() {
        super.onCreate()
        apiServiceComponent = DaggerApiServiceComponent.builder()
            .apiServiceModule(ApiServiceModule())
            .build()
    }

    fun getApiServiceComponent(): ApiServiceComponent {
        return apiServiceComponent
    }
}