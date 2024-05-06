package com.example.assignment_4

import android.app.Application
import androidx.room.Room

class App : Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "jokes.db").build()
    }
}
