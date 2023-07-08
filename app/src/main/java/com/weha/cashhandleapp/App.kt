package com.weha.cashhandleapp

import android.app.Application
import com.weha.cashhandleapp.handler.GlobalExceptionHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this)
    }

}