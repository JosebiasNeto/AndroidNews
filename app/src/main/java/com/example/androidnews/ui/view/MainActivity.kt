package com.example.androidnews.ui.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnews.R
import com.example.androidnews.data.api.APIHelper
import com.example.androidnews.data.api.RetrofitBuilder
import com.example.androidnews.data.model.Article
import com.example.androidnews.databinding.ActivityMainBinding
import com.example.androidnews.ui.ArticleAdapter
import com.example.androidnews.ui.viewmodel.MainViewModel
import com.example.androidnews.ui.viewmodel.ViewModelFactory
import com.example.androidnews.utils.OnItemClickListener
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

        binding.recyclerview.addOnItemClickListener(object : OnItemClickListener{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onItemClicked(position: Int, view: View) {
                openFullArticle(position)
            }
        })

        val pullToRefresh = binding.pullToRefresh
        pullToRefresh.setOnRefreshListener {
            setupViewModel()
            setupUI()
            setupObservers()
            pullToRefresh.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_action)
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager
            .getSearchableInfo(componentName))
        searchView.maxWidth

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter.getFilter().filter(query)
                return false
            }
        })
        return true
    }

    private fun setupObservers() {
        viewModel.getArticles().observe(this, {
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun openFullArticle(idFullArticle: Int) {
        val intent = Intent(this,FullArticle::class.java)
        val a: Article = adapter.getArticle(idFullArticle)
        intent.putExtra("name", a.source.name)
        intent.putExtra("author", a.author)
        intent.putExtra("title", a.title)
        intent.putExtra("description", a.description)
        intent.putExtra("image", a.image)
        intent.putExtra("data", a.data)
        startActivity(intent)
    }
}







