package com.example.androidnews

import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnews.databinding.ActivityFullArticleBinding
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class FullArticle : AppCompatActivity() {
    private lateinit var binding: ActivityFullArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btDownload.setOnClickListener(View.OnClickListener {
           binding.ivImage.invalidate()
           val bitmap = (binding.ivImage.getDrawable() as BitmapDrawable).bitmap
           saveToInternalStorage(bitmap, binding.tvTitle.text.toString())
       })
        binding.btShare.setOnClickListener(View.OnClickListener {

        })


        binding.tvAuthor.text = intent.getStringExtra("author")
        binding.tvTitle.text = intent.getStringExtra("title")
        binding.tvDescription.text = intent.getStringExtra("description")
        var image = intent.getStringExtra("image")
        Picasso.get().load(image).into(binding.ivImage)
        binding.tvDate.text = intent.getStringExtra("data")


    }

    private fun saveToInternalStorage(bitmapImage: Bitmap, name: String) {
        val cw = ContextWrapper(applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val directory: File? = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        // Create imageDir
        val mypath = File(directory, name+".jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (fos != null) {
                    fos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

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
