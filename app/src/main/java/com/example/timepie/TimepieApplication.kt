package com.example.timepie

import android.app.Application
import com.example.timepie.models.Supplement
import com.parse.Parse
import com.parse.ParseObject

class TimepieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ParseObject.registerSubclass(Supplement::class.java)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}