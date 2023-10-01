package com.ridhamsharma.colorlistdatabinding

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerColorView() : RecyclerView.Adapter<RecyclerColorView.ViewHolder>() {
    var itemCount1 : Int = 0
    var color1:String = "#ffff00"
    var color2: String = "#ffd000"
    fun updateView( itemCount1 : Int,  color1:String,color2: String){
        this.color1 = color1
        this.color2 = color2
        this.itemCount1 = itemCount1
        notifyDataSetChanged()
    }
    class ViewHolder( view : View) : RecyclerView.ViewHolder(view) {
        var tvrecyclerView = view.findViewById<TextView>(R.id.tvrecyclerView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.recyclerlistview,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemCount1

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position %2==0) {
            holder.tvrecyclerView.setBackgroundColor(Color.parseColor(color1))
        }else{
            holder.tvrecyclerView.setBackgroundColor(Color.parseColor(color2))
        }


    }
}