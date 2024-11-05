package com.example.projeto.data

import android.app.Application

class AppApplication : Application() {
    lateinit var connectDb:AppDatabase
    override fun onCreate() {
        super.onCreate()
        connectDb = AppDatabase.getDatabase(this)
    }
}