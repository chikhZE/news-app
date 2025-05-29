package com.example.news

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val news = ArrayList<NewsModel>()
        news.add(NewsModel("name","name","name","name","name"))
        news.add(NewsModel("name","name","name","name","name"))
        news.add(NewsModel("name","name","name","name","name"))

        val manager = LinearLayoutManager(this)
        binding.newsList.layoutManager = manager
        val adapter = NewsAdapter(this,news)
        binding.newsList.adapter = adapter

    }
}