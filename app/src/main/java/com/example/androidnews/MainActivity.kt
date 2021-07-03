package com.example.androidnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.databinding.ActivityFullArticleBinding
import com.example.androidnews.databinding.ActivityMainBinding
import model.Articles

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private inner class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: Articles){
            itemView.findViewById<TextView>(R.id.tv_name).text = article.name
            itemView.findViewById<TextView>(R.id.tv_author).text = article.author
            itemView.findViewById<TextView>(R.id.tv_title).text = article.title
        }
    }

    private inner class ArticleAdapter(private val articles: MutableList<Articles>): RecyclerView.Adapter<ArticleHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
           return ArticleHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
        }

        override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
            val article = articles[position]
            holder.bind(article)
        }

        override fun getItemCount(): Int = articles.size
    }

    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articles: MutableList<Articles> = ArrayList()
        for (a in 0..10){
            val article = Articles()
            article.name = "Name$a"
            article.author = "Author$a"
            article.title = "Title$a"
            articles.add(article)
        }

        articleAdapter = ArticleAdapter(articles)
        findViewById<RecyclerView>(R.id.recyclerview).adapter = articleAdapter
        findViewById<RecyclerView>(R.id.recyclerview).layoutManager = LinearLayoutManager(applicationContext)

        val recycler_articles = findViewById<RecyclerView>(R.id.recyclerview)
        recycler_articles.layoutManager = GridLayoutManager(applicationContext, 3)


    }

    private fun FullArticleFun(){
        val intent = Intent(this, FullArticle::class.java)
        startActivity(intent)
    }



}