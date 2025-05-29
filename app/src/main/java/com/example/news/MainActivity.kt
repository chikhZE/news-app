package com.example.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.news.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val news = ArrayList<NewsModel>()


        binding.searchBtn.setOnClickListener {
            var SearchValue = binding.searchBar.text
            if (SearchValue.toString().trim() != ""){
                fetchData("https://gnews.io/api/v4/search?q=${SearchValue}&apikey=a86e5b8b2a10a732d7be4f76ebeea22c",news)

            }
        }


    }
    private fun fetchData(urlString: String, arrayList: ArrayList<NewsModel>) {
        binding.error.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        thread {
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseText = connection.inputStream.bufferedReader().readText()
                val root = JSONObject(responseText)
                val jsonArray = root.getJSONArray("articles")

                arrayList.clear()
                for (i in 0 until jsonArray.length()) {
                    val article = jsonArray.getJSONObject(i)
                    val title = article.getString("title")
                    val description = article.getString("description")
                    val url = article.getString("url")
                    val publishedAt = article.getString("publishedAt")
                    val image = article.getString("image")


                    arrayList.add(NewsModel(title, description, url, publishedAt,image))
                }

                // Update UI on main thread
                runOnUiThread {
                    val adapter = NewsAdapter(this, arrayList)
                    binding.newsList.layoutManager = LinearLayoutManager(this)
                    binding.newsList.adapter = adapter
                    binding.progressBar.visibility = View.GONE

                }

            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    binding.error.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE

                }
            }
        }
    }

}