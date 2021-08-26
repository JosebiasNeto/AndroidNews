package com.example.androidnews.ui.view

import com.example.androidnews.utils.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnews.data.api.APIHelper
import com.example.androidnews.data.api.RetrofitBuilder
import com.example.androidnews.databinding.ActivityMainBinding
import com.example.androidnews.data.model.Articles
import com.example.androidnews.ui.ArticleAdapter
import com.example.androidnews.ui.viewmodel.MainViewModel
import com.example.androidnews.ui.viewmodel.ViewModelFactory
import com.example.androidnews.utils.Status
import com.example.androidnews.utils.addOnItemClickListener



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUI()
        setupObservers()

       // val recycler_articles = binding.recyclerview
        ///recycler_articles.layoutManager = LinearLayoutManager(applicationContext)

       // recycler_articles.addOnItemClickListener(object : OnItemClickListener {
       //     override fun onItemClicked(position: Int, view: View) {
                //openFullArticle(position)
       //     }
      //  })

        val pullToRefresh = binding.progressBar
        pullToRefresh.setOnRefreshListener {
            setupViewModel()
            setupUI()
            setupObservers()
            pullToRefresh.isRefreshing = false
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(APIHelper(RetrofitBuilder.articlesAPI))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(arrayListOf())
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerview.context,
                (binding.recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerview.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getArticles().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerview.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { articles -> retrieveList(articles as ArrayList<Articles>) }
                    }
                    Status.ERROR -> {
                        binding.recyclerview.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerview.visibility = View.GONE
                    }
                }
            }

        })
    }

    private fun retrieveList(articles: ArrayList<Articles>){
        adapter.apply {
            addArticles(articles)
            notifyDataSetChanged()
        }
    }




/*
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

        viewModel = ViewModelProvider(this,
            MainViewModel.MainViewModelFactory(ArticlesAPI())
        ).get(MainViewModel::class.java)
        viewModel.articlesLiveData.observe(this, Observer { articles ->
            binding.recyclerview.adapter = ArticleAdapter(arrayListOf())
        })
        viewModel.getArticles()



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
        })*/

}


