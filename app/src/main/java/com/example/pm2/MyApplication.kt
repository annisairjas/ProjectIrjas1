package com.example.pm2

import android.app.Application
import com.example.pm2.DatabaseHandler


class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        DatabaseHandler.initDatabaseInstance(this)
    }

}