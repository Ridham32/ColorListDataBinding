package com.ridhamsharma.colorlistdatabinding

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ridhamsharma.colorlistdatabinding.databinding.ActivityMainBinding
import com.ridhamsharma.colorlistdatabinding.databinding.CustomfabBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var colorSharedPref: SharedPreferences
    lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ColorSingleton.sharedPref.initPrefs(this)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        colorSharedPref = getSharedPreferences(resources.getString(R.string.app_name),Context.MODE_PRIVATE)
        editor = colorSharedPref.edit()
        binding.mainActivity = this
    }
    fun onFabClock(){
        var dialog = Dialog(this)
        var dialogBinding =CustomfabBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.getWindow()?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        }
    }





