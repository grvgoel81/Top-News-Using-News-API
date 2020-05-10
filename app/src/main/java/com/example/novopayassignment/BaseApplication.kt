package com.example.novopayassignment

import android.app.Application
import com.example.novopayassignment.di.ApiComponent
import com.example.novopayassignment.di.ApiHelper
import com.example.novopayassignment.di.AppModule
import com.example.novopayassignment.di.DaggerApiComponent

class BaseApplication : Application()
{

    lateinit var mApiComponent: ApiComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        mApiComponent = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiHelper(ApiHelper())
            .build()

    }

    companion object {
        lateinit var app: BaseApplication
            private set
    }

}