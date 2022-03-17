package com.intive.patronage22.intivi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R


class RecyclerAdapater:RecyclerView.Adapter<RecyclerAdapater.ViewHolder>() {

    private var titles = arrayOf("Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure","Moonfal","Sing 2","The Expanse","Death on the Nile","Little Nicholas' Treasure")
    private var images = intArrayOf(R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure,R.drawable.avatar_moonfall, R.drawable.avatar_sing_2,R.drawable.avatar_the_expanse,R.drawable.avatar_death_on_the_nile,R.drawable.avatar_little_nicholas_treasure)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapater.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapater.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
    }
}