package com.ridhamsharma.colorlistdatabinding

import android.content.Context
import android.content.SharedPreferences

class ColorSharedPref {
    var sharedPref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null


    fun initPrefs(context: Context) {
        if (sharedPref == null) {
            sharedPref = context.getSharedPreferences(
                context.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            editor = sharedPref?.edit()
        }
    }

    fun saveString(key: String, value: String) {
        editor?.putString(key, value)
        editor?.commit()
        editor?.apply()
    }

    fun getString(key: String): String {
        return sharedPref?.getString(key, "") ?: ""


    }


    fun clearPrefs() {
        editor?.clear()
        editor?.commit()
    }


}