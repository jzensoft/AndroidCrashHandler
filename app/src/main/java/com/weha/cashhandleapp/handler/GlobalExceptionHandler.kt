package com.weha.cashhandleapp.handler

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.weha.cashhandleapp.activity.CrashActivity
import kotlin.system.exitProcess

class GlobalExceptionHandler(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler,
    private val activityBeLaunched: Class<*>
) : Thread.UncaughtExceptionHandler {
    companion object {
        const val TAG = "GlobalExceptionHandler"
        const val CASH_DATA = "CASH_DATA"

        fun initialize(
            applicationContext: Context,
        ) {
            val handler = Thread.getDefaultUncaughtExceptionHandler()?.let {
                GlobalExceptionHandler(
                    applicationContext,
                    it,
                    CrashActivity::class.java
                )
            }
            Thread.setDefaultUncaughtExceptionHandler(handler)
        }

        fun getThrowableFromIntent(intent: Intent): Throwable? {
            return try {
                Gson().fromJson(intent.getStringExtra(CASH_DATA), Throwable::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "getThrowableFromIntent:${e.message}")
                return null
            }
        }
    }

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        try {
            launchActivity(applicationContext, activityBeLaunched, p1)
            exitProcess(0)
        } catch (e: Exception) {
            defaultHandler.uncaughtException(p0, p1)
        }
    }

    private fun launchActivity(
        applicationContext: Context,
        activity: Class<*>,
        exception: Throwable
    ) {
        val intent = Intent(applicationContext, activity).also {
            it.putExtra(CASH_DATA, Gson().toJson(exception))
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(intent)
    }
}