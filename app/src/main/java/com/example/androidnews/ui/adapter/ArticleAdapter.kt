package com.example.androidnews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.R
import com.example.androidnews.data.model.Articles
import com.squareup.picasso.Picasso

class ArticleAdapter(private val articles: ArrayList<Articles>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Articles) {
            itemView.apply {
                findViewById<TextView>(R.id.tv_name).text = article.source.name
                findViewById<TextView>(R.id.tv_author).text = "By: " + article.author
                findViewById<TextView>(R.id.tv_title).text = article.title
                Picasso.get().load(article.image).into(itemView.findViewById<ImageView>(R.id.iv_image))
            }}}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder =
        ArticleHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false))

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) =
        holder.bind(articles[position])

    override fun getItemCount(): Int = articles.size

    fun addArticles(articles: List<Articles>){
        this.articles.apply {
            clear()
            addAll(articles)
        }
    }

}




