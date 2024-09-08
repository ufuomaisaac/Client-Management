package com.example.clientmanager.core.startup

import android.content.Context
import androidx.startup.Initializer
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


//yet to be handled
class TimberInitializer : Initializer<Unit> {

    //Use method to initialize your component that is timber
    override fun create(context: Context) {
        TODO("Not yet implemented")
        // Initialize Timber here. Example:
        /*if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }*/
    }


    //only if the return type of Initializer is Timber
   /* override fun create(context: Context): Timber {
        TODO("Not yet implemented")
    }*/

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}