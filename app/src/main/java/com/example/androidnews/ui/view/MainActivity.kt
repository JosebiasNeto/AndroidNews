package com.example.androidnews.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnews.data.api.APIHelper
import com.example.androidnews.data.api.RetrofitBuilder
import com.example.androidnews.databinding.ActivityMainBinding
import com.example.androidnews.data.model.Article
import com.example.androidnews.ui.ArticleAdapter
import com.example.androidnews.ui.viewmodel.MainViewModel
import com.example.androidnews.ui.viewmodel.ViewModelFactory
import com.example.androidnews.utils.Status
import kotlinx.coroutines.delay


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
                        resource.data?.articles?.let { it1 -> refreshAdapter(it1) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }

        })
    }

    private fun refreshAdapter(articles: ArrayList<Article>) {
        adapter.apply {
            addArticles(articles)
            notifyDataSetChanged()
        }
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

*/

