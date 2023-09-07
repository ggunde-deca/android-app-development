package com.example.moviedatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedatabase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        // when app is initially opened the Fragment 1 should be visible
        supportFragmentManager.beginTransaction().apply {
            replace(_binding.fragmentHolder.id, RecycleFragment())
            addToBackStack(null)
            commit()
        }
    }
}