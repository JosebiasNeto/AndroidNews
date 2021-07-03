package Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.databinding.ArticleItemBinding
import model.Articles



class ArticlesAdapter(val articles: MutableList<Articles>):RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder{
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        with(holder){
            with(articles[position]){

            }
        }
    }

    override fun getItemCount(): Int = articles.size

    class ArticlesViewHolder(val binding: ArticleItemBinding): RecyclerView.ViewHolder(binding.root){

    }
}