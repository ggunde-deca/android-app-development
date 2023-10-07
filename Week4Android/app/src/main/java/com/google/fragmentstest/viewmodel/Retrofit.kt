package com.google.fragmentstest.viewmodel;

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


fun main() {
    // Just for Testing
    println("The Barbie movie poster path is: " + getImageUsingRetrofit("barbie"))
}

interface MovieService {
    @GET("movie/popular?api_key=3e4e6775a71a4e213c6430b6f4cdb740")
    fun listRepos(): Call<ApiMovieList?>?
}

fun getImageUsingRetrofit(movieName: String): String? {
    var retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    val service: MovieService = retrofit.create<MovieService>()

    for (movie in service.listRepos()?.execute()?.body()?.results!!) {
        if (movie.original_title.lowercase() == movieName) {
            return Constants.MOVIE_PATH + movie.poster_path
        }
    }

    return null
}

