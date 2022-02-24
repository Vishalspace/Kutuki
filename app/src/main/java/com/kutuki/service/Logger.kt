package com.kutuki.service

import android.util.Log

class Logger(val className: String) {
    fun loge(message: String) {
        Log.e(className, message)
    }
}