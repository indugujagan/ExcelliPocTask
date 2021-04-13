package com.example.samplepoc.util

import android.util.Log
import com.example.samplepoc.BuildConfig

class Logger {

    companion object {

        private val APP_TAG = "SamplePoc"

        fun logite(tag: String, msg: String?) {
            if (disableLogs()) return
            Log.e("$APP_TAG $tag", msg ?: "NULL MESSAGE")
        }

        fun disableLogs(): Boolean {
            return !BuildConfig.DEBUG
        }
    }

}