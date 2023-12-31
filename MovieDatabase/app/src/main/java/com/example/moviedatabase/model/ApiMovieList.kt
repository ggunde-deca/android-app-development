package com.example.moviedatabase.api.domain

import com.example.moviedatabase.model.ApiMovie

data class ApiMovieList(
    val page: Int,
    val results: List<ApiMovie>,
    val total_pages: Int,
    val total_results: Int
)