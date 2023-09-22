package com.example.week2android

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.week2android.apiMovie.getImageUsingRetrofit
import com.example.week2android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.button.setOnClickListener {
            // Way 1 to set Image
//            val newImageDrawable: Drawable? = getDrawable( R.drawable.image1)
            val newImageDrawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.image1)
            _binding.imageView.setImageDrawable(newImageDrawable)

            setImageUsingGlide()
        }
    }
    private fun setImageUsingGlide(){
        val imagePath: String? = getImageUsingRetrofit()

        Glide
            .with(this)
            .load(imagePath)
            .centerCrop()
            .into(_binding.imageView);
    }
}