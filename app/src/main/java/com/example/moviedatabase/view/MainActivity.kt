package com.example.moviedatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviedatabase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedatabase.view.MovieAdapter
import com.example.moviedatabase.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.moviesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = MovieAdapter()
        _binding.moviesRecyclerView.adapter = adapter

        adapter.updateData(viewmodel.getMockData())

        // previous template
        // setContentView(R.layout.activity_main)
    }
}