package com.example.androidnews.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.R
import com.example.androidnews.model.Articles
import com.squareup.picasso.Picasso

class ArticleAdapter(private val articles: List<Articles>?) :
    RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Articles) {
            itemView.findViewById<TextView>(R.id.tv_name).text = article.source.name
            itemView.findViewById<TextView>(R.id.tv_author).text = "By: " + article.author
            itemView.findViewById<TextView>(R.id.tv_title).text = article.title
            Picasso.get().load(article.image).into(itemView.findViewById<ImageView>(R.id.iv_image));
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articles?.get(position)
        if (article != null) holder.bind(article)
    }

    override fun getItemCount(): Int {
        if (articles != null) {
            return articles.size
        }
        return 0
    }
}