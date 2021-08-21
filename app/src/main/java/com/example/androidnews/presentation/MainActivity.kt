package com.example.androidnews.presentation

import com.example.androidnews.OnClick.OnItemClickListener
import com.example.androidnews.OnClick.addOnItemClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.data.NetworkUtils
import com.example.androidnews.R
import com.example.androidnews.data.ArticlesAPI
import com.example.androidnews.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import com.example.androidnews.model.Articles
import com.example.androidnews.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var articlesList: List<Articles>;
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        val recycler_articles = binding.recyclerview
        recycler_articles.layoutManager = LinearLayoutManager(applicationContext)

        recycler_articles.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                openFullArticle(position)
            }
        })

        val pullToRefresh = binding.pullToRefresh
        pullToRefresh.setOnRefreshListener {
            getData()
            pullToRefresh.isRefreshing = false
        }
    }

    private fun openFullArticle(idFullArticle: Int) {
        val intent = Intent(this, FullArticle::class.java)
        val a: Articles = articlesList[idFullArticle]
        intent.putExtra("name", a.source.name)
        intent.putExtra("author", a.author)
        intent.putExtra("title", a.title)
        intent.putExtra("description", a.description)
        intent.putExtra("image", a.image)
        intent.putExtra("data", a.data)
        startActivity(intent)
    }

    fun getData() {
        val retrofitClient = NetworkUtils()
            .getRetrofitInstance("https://newsapi.org/v2/")

        val endpoint = retrofitClient.create(ArticlesAPI::class.java)
        val callback = endpoint.getArticles()

        callback.enqueue(object : Callback<ArticlesResponse> {
            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.body() != null) {
                    articlesList = response.body()?.articles!!
                    binding.recyclerview.adapter = ArticleAdapter(articlesList)
                }
            }
        })
    }
}


