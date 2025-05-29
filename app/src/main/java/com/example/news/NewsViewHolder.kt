package com.example.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val img : ImageView = itemView.findViewById(R.id.img)
    val date : TextView = itemView.findViewById(R.id.date)
    val title : TextView = itemView.findViewById(R.id.titleText)
    val description : TextView = itemView.findViewById(R.id.description)
    val url : TextView = itemView.findViewById(R.id.url)

}