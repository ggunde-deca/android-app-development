package com.google.fragmentstest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {

    private val imageUrl = MutableLiveData<String>()
    private var movieName: String = "barbie"

    fun getImageUrl() : LiveData<String> {
        return imageUrl
    }

    fun getMovieName (): String {
        return movieName
    }

    init {
        imageUrl.value = "https://image.tmdb.org/t/p/original/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg"
        movieName = "barbie"
    }

    fun set(data: String) {
        imageUrl.value = data
    }

    fun setMovieName (data: String) {
        movieName = data
    }
}

