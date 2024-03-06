package com.drg.rustandrevolt.domain.service

import android.content.Context

object AppContextSingleton {
    private lateinit var context: Context

    fun setContext(appContext: Context) {
        context = appContext
    }

    fun getContext(): Context {
        return context
    }
}