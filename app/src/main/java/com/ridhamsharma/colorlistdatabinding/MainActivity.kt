package com.ridhamsharma.colorlistdatabinding

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.ridhamsharma.colorlistdatabinding.databinding.ActivityMainBinding
import com.ridhamsharma.colorlistdatabinding.databinding.CustomfabBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
  //  lateinit var colorSharedPref: SharedPreferences
    //lateinit var editor: Editor
    var itemCount : Int = 0
    var colorValue: String = ""
    lateinit var dialog: Dialog
    lateinit var layoutManager : LinearLayoutManager
    var adapter : RecyclerColorView?=null
    lateinit var dialogBinding: CustomfabBinding
    var color1 : String = ""
    var color2 :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ColorSingleton.sharedPref.initPrefs(this)
        getColor()
        layoutManager = LinearLayoutManager(this)
        adapter = RecyclerColorView(itemCount,color1,color2)
        binding.recyclerListView.layoutManager = layoutManager
        binding.recyclerListView.adapter = adapter
      //  colorSharedPref = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
       // editor = colorSharedPref.edit()
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
                   AppConstants.color,
                    dialogBinding.etColor1.text.toString()
                )
                ColorSingleton.sharedPref.saveString(
                    AppConstants.color2,
                    dialogBinding.etColor2.text.toString()
                )
                ColorSingleton.sharedPref.saveInt(
                    AppConstants.number,
                    dialogBinding.etListCount.text.toString().toInt()
                )
                dialog.dismiss()
                getColor()
                adapter?.notifyDataSetChanged()

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

fun getColor(){
    itemCount = ColorSingleton.sharedPref.getInt(AppConstants.number)
    color1 = ColorSingleton.sharedPref.getString(AppConstants.color)
    color2 = ColorSingleton.sharedPref.getString(AppConstants.color2)
}


}






