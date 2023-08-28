package com.ridhamsharma.colorlistdatabinding

import android.content.SharedPreferences

object ColorSingleton {

    init {
        println("in singleton init")
    }
    lateinit var sharedPref: ColorSharedPref
}