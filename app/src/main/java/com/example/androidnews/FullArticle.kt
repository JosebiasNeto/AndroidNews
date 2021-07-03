package com.example.androidnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnews.databinding.ActivityFullArticleBinding
import model.FullArticles
import model.addFullArticles

class FullArticle : AppCompatActivity() {
    private lateinit var binding: ActivityFullArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bindingArticles = ActivityFullArticleBinding.bind(findViewById(R.id.layout_full_article))
        val DialogView = LayoutInflater.from(baseContext).inflate(R.layout.activity_full_article, null)
        val builder = AlertDialog.Builder(baseContext)
            .setView(DialogView)
        Adapter.FullArticlesAdapter(addFullArticles())

        val name = intent.getStringExtra("name")
        findViewById<TextView>(R.id.tv_name).text = name
        val author = intent.getStringExtra("author")
        findViewById<TextView>(R.id.tv_author).text = author
        val title = intent.getStringExtra("title")
        findViewById<TextView>(R.id.tv_title).text = title
        val description = intent.getStringExtra("description")
        findViewById<TextView>(R.id.tv_description).text = description
        val image = intent.getStringExtra("image")
       // findViewById<ImageView>(R.id.iv_image).setImageResource(0) = image
        val data = intent.getStringExtra("data")
        findViewById<TextView>(R.id.tv_date).text = data

    }



    /*
    private inner class FullArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(fullarticle: FullArticles){
            itemView.findViewById<TextView>(R.id.tv_name).text = fullarticle.name
            itemView.findViewById<TextView>(R.id.tv_author).text = fullarticle.author
            itemView.findViewById<TextView>(R.id.tv_title).text = fullarticle.title
            itemView.findViewById<TextView>(R.id.tv_description).text = fullarticle.description
            itemView.findViewById<TextView>(R.id.iv_image).text = fullarticle.image.toString()
            itemView.findViewById<TextView>(R.id.tv_date).text = fullarticle.date
        }
    }

    private inner class FullArticleAdapter(private val fullarticles: MutableList<FullArticles>): RecyclerView.Adapter<FullArticleHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullArticleHolder {
            return FullArticleHolder(layoutInflater.inflate(R.layout.activity_full_article, parent, false))
        }

        override fun onBindViewHolder(holder: FullArticleHolder, position: Int) {
            val fullarticle = fullarticles[position]
            holder.bind(fullarticle)
        }

        override fun getItemCount(): Int = fullarticles.size
    }



    private lateinit var fullarticleAdapter: FullArticleAdapter

*/
}
