package com.kutuki.utils

import android.util.Log

class Logger(val className: String) {
    fun error(message: String, t: Throwable? = null) {
        Log.e(className, message, t)
    }

    fun debug(message: String) {
        Log.d(className, message)
    }
}