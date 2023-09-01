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
    var colorValue: String = ""
    lateinit var dialog: Dialog
    lateinit var dialogBinding: CustomfabBinding
    var color1 : String = ""
    var color2 :String =""

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
        dialog = Dialog(this)
        dialogBinding = CustomfabBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.getWindow()?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.mainActivityCustom = this
        dialogBinding.etColor1.setText(colorValue)
        dialogBinding.etColor2.setText(colorValue)
        dialogBinding.btnCustomAdd.setOnClickListener {
            if (dialogBinding.etColor1.text.isNullOrEmpty()) {
                dialogBinding.etColor1.error = "Choose the color"
            } else if (dialogBinding.etColor1.text.isNullOrEmpty()) {
                dialogBinding.etColor2.error = "Choose the color"
            } else if (dialogBinding.etListCount.text.isNullOrEmpty()) {
                dialogBinding.etListCount.error = "Choose the color"

                //
            } else {
                ColorSingleton.sharedPref.saveString(
                    "color1",
                    dialogBinding.etColor1.text.toString()
                )
                ColorSingleton.sharedPref.saveString(
                    "color2",
                    dialogBinding.etColor2.text.toString()
                )
                ColorSingleton.sharedPref.saveString(
                    "color2",
                    dialogBinding.etListCount.text.toString()
                )
                dialog.dismiss()
            }
        }
        dialog.show()
    }
    fun onColorPick(type: Int) {

        ColorPickerDialog
            .Builder(this)                        // Pass Activity Instance
            .setTitle("Pick Theme")            // Default "Choose Color"
            .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
            .setDefaultColor(R.color.white)     // Pass Default Color
            .setColorListener { color, colorHex ->
                if (type == 1)
                    dialogBinding.color1 = colorHex
                else
                    dialogBinding.color2 = colorHex
                System.out.println("The color getString is.... $colorValue")
                System.out.println("The color choosed is.... $color")
            }


            .show()
    }


}






