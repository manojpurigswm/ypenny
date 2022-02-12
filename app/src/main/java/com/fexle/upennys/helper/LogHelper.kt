package com.fexle.upennys.helper

import android.util.Log

fun showDebugLog(log: String) {
    Log.d("DebugLog", "debug: $log")
}

fun showErrorLog(log: String) {
    Log.e("ErrorLog", "error: $log")
}