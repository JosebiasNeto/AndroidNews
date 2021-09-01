package com.example.androidnews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.R
import com.example.androidnews.data.model.Article
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ArticleAdapter(private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleHolder>(), Filterable {

    var articlesFilter: ArrayList<Article> = ArrayList()
    init {
        articlesFilter = articles
    }

    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            itemView.apply {
                findViewById<TextView>(R.id.tv_name).text = article.source.name
                findViewById<TextView>(R.id.tv_author).text = "By: " + article.author
                findViewById<TextView>(R.id.tv_title).text = article.title
                Picasso.get().load(article.image).into(itemView.findViewById<ImageView>(R.id.iv_image))
            }}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder =
        ArticleHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false))

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) =
        holder.bind(articlesFilter[position])

    override fun getItemCount(): Int = articlesFilter.size

    fun addArticles(articles: List<Article>){
        this.articles.apply {
            clear()
            addAll(articles)
        }
    }

    fun getArticle(position: Int) = articlesFilter[position]


    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()){
                    articlesFilter = articles
                } else {
                    var resultList: MutableList<Article> = ArrayList()
                    for (art in articles){

//                        if (art.author.lowercase(Locale.ROOT).contains(
//                                charSearch.lowercase(Locale.ROOT)) ||
//                            art.title.lowercase(Locale.ROOT).contains(
//                                charSearch.lowercase(Locale.ROOT)) ||
//                            art.description.lowercase(Locale.ROOT).contains(
//                                charSearch.lowercase(Locale.ROOT)) ||
//                            art.data.lowercase(Locale.ROOT).contains(
//                                charSearch.lowercase(Locale.ROOT))
//                            ){
//                            resultList.add(art)
//                        }
                        articlesFilter = resultList as ArrayList<Article>
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = articlesFilter
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values != null) {
                    articlesFilter = results.values as ArrayList<Article>
                    notifyDataSetChanged()
                }
            }
        }
    }
}






