package Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.databinding.ArticleItemBinding
import model.FullArticles


class FullArticlesAdapter(val fullarticles: MutableList<FullArticles>):RecyclerView.Adapter<FullArticlesAdapter.FullArticlesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullArticlesViewHolder{
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FullArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FullArticlesViewHolder, position: Int) {
        with(holder){
            with(fullarticles[position]){

            }
        }
    }

    override fun getItemCount(): Int = fullarticles.size

    class FullArticlesViewHolder(val binding: ArticleItemBinding): RecyclerView.ViewHolder(binding.root){

    }
}