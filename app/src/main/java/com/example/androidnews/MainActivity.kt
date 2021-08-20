package com.example.androidnews

import OnClick.OnItemClickListener
import OnClick.addOnItemClickListener
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
import com.example.androidnews.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import model.Articles
import model.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    private lateinit var articlesList : List<Articles>;

    private lateinit var binding: ActivityMainBinding
    private inner class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: Articles){
            itemView.findViewById<TextView>(R.id.tv_name).text = article.source.name
            itemView.findViewById<TextView>(R.id.tv_author).text = "By: " + article.author
            itemView.findViewById<TextView>(R.id.tv_title).text = article.title
            Picasso.get().load(article.image).into(itemView.findViewById<ImageView>(R.id.iv_image));
        }
    }

    private inner class ArticleAdapter(private val articles: List<Articles>?):
        RecyclerView.Adapter<ArticleHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
           return ArticleHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
        }

        override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
            val article = articles?.get(position)
            if (article != null) {
                holder.bind(article)
            }
        }

        override fun getItemCount(): Int {
            if (articles != null) {
                return articles.size
            }
            return 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        val recycler_articles = binding.recyclerview
        recycler_articles.layoutManager = LinearLayoutManager(applicationContext)

        recycler_articles.addOnItemClickListener(object: OnItemClickListener {
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



    private fun openFullArticle(idFullArticle: Int){
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

    fun getData(){
        val retrofitClient = NetworkUtils()
            .getRetrofitInstance("https://newsapi.org/v2/")

        val endpoint = retrofitClient.create(ArticlesAPI::class.java)
        val callback = endpoint.getArticles()

        callback.enqueue(object : Callback<ArticlesResponse> {
            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                if (response.body() != null) {
                    articlesList = response.body()?.articles!!
                    binding.recyclerview.adapter = ArticleAdapter(articlesList)
                }
            }
        })
    }
}


