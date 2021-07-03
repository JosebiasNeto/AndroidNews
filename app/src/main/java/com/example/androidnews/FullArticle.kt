package com.example.androidnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidnews.databinding.ActivityFullArticleBinding

class FullArticle : AppCompatActivity() {
    private lateinit var binding: ActivityFullArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}