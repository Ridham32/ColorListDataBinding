package com.ridhamsharma.colorlistdatabinding

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerColorView(var itemCount1 : Int, var color1:String,var color2: String) : RecyclerView.Adapter<RecyclerColorView.ViewHolder>() {
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