package com.example.androidnews.presentation

import android.Manifest
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.androidnews.databinding.ActivityFullArticleBinding
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class FullArticle : AppCompatActivity() {
    private lateinit var binding: ActivityFullArticleBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btDownload.setOnClickListener(View.OnClickListener {
            saveFile()
        })
        binding.btShare.setOnClickListener(View.OnClickListener {
            try {
                binding.ivImage.invalidate()
                val bitmap = (binding.ivImage.drawable as BitmapDrawable).bitmap
                val cachePath: File = File(applicationContext.getCacheDir(), "images")
                cachePath.mkdirs() // don't forget to make the directory
                val stream =
                    FileOutputStream("$cachePath/image.png") // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                stream.close()

                val imagePath: File = File(applicationContext.getCacheDir(), "images")
                val newFile = File(imagePath, "image.png")
                val contentUri: Uri? =
                    FileProvider.getUriForFile(applicationContext, "com.example.androidnews.fileprovider", newFile)

                if (contentUri != null) {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // temp permission for receiving app to read this file
                    shareIntent.setDataAndType(contentUri, contentResolver.getType(contentUri))
                    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                    startActivity(Intent.createChooser(shareIntent, "Choose an app"))
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        })

        binding.tvAuthor.text = intent.getStringExtra("author")
        binding.tvTitle.text = intent.getStringExtra("title")
        binding.tvDescription.text = intent.getStringExtra("description")
        var image = intent.getStringExtra("image")
        Picasso.get().load(image).into(binding.ivImage)
        binding.tvDate.text = intent.getStringExtra("data")

        registerForActivityResult(
            RequestPermission()
        ) { isGranted: Boolean ->

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun saveFile() = run {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            binding.ivImage.invalidate()
            val bitmap = (binding.ivImage.drawable as BitmapDrawable).bitmap
            saveToInternalStorage(bitmap, binding.tvTitle.text.toString())
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    saveFile()
                } else {

                }
                return
            }

            else -> {
            }
        }
    }


    private fun saveToInternalStorage(bitmapImage: Bitmap, name: String) {
        val cw = ContextWrapper(applicationContext)

        val directory: File? =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        val mypath = File(directory, name + ".jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            Toast.makeText(applicationContext, "Imagem salva", Toast.LENGTH_SHORT).show()
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
}
