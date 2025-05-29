package com.example.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(
    val context : Context,
    val data : ArrayList<NewsModel>
):RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.news_box,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.date.text = data[position].publishedAt
        holder.description.text = data[position].description
        holder.url.text = data[position].url
        Picasso.get().load(data[position].image).into(holder.img)


    }

}