package com.ridhamsharma.colorlistdatabinding

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.ridhamsharma.colorlistdatabinding.databinding.ActivityMainBinding
import com.ridhamsharma.colorlistdatabinding.databinding.CustomfabBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var colorSharedPref: SharedPreferences
    lateinit var editor: Editor

    //     lateinit var dialog: CustomfabBinding
    lateinit var colorValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ColorSingleton.sharedPref.initPrefs(this)
        colorSharedPref =
            getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        editor = colorSharedPref.edit()
        binding.mainActivity = this

    }

    fun onFabClick() {
        var dialog = Dialog(this)
        var dialogBinding = CustomfabBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.getWindow()?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.mainActivityCustom = this
        dialogBinding.etColor1.setText(colorValue)

        dialog.show()

    }


    fun onColorPick() {

        ColorPickerDialog
            .Builder(this)                        // Pass Activity Instance
            .setTitle("Pick Theme")            // Default "Choose Color"
            .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
            .setDefaultColor(R.color.white)     // Pass Default Color
            .setColorListener { color, colorHex ->
                ColorSingleton.sharedPref.saveString("color", colorHex)
                colorValue = ColorSingleton.sharedPref.getString("color")
                System.out.println("The color getString is.... $colorValue")
                System.out.println("The color choosed is.... $color")
            }


            .show()
    }


}






