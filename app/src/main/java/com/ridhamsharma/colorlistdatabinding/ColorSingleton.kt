package com.ridhamsharma.colorlistdatabinding


object ColorSingleton {

    init {
        println("in singleton init")
    }
    var sharedPref = ColorSharedPref()
}