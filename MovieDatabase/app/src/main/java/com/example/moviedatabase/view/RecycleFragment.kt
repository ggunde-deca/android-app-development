package com.example.moviedatabase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedatabase.databinding.RecycleFragmentBinding
import com.example.moviedatabase.viewmodel.MainViewModel
import androidx.fragment.app.viewModels
import com.example.moviedatabase.R


class RecycleFragment: Fragment(R.layout.recycle_fragment) {
    private lateinit var _binding: RecycleFragmentBinding

    val viewmodel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate the layout and bind to the _binding
        _binding = RecycleFragmentBinding.inflate(inflater, container, false)

        _binding.moviesRecyclerView.layoutManager = GridLayoutManager(this.context, 2)

        val adapter = MovieAdapter()
        _binding.moviesRecyclerView.adapter = adapter

        adapter.updateData(viewmodel.getMockData())

        // Inflate the layout for this fragment
        return _binding.root
    }
}