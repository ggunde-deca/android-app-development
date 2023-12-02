package com.weather.forecast.clearsky.network

import com.weather.forecast.clearsky.model.WeatherImageModel
import com.weather.forecast.clearsky.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET(NetworkingConstants.GET_WEATHER)
    suspend fun getWeatherData(
        @Query("q") city: String
    ): WeatherModel?

    @GET(NetworkingConstants.BASE_IMAGE_URL + "{city} {condition}")
    suspend fun getWeatherImageData(@Path("city") city: String, @Path("condition") condition: String): WeatherImageModel?
}