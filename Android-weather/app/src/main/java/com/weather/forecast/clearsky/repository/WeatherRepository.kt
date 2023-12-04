package com.weather.forecast.clearsky.repository

import com.weather.forecast.clearsky.model.WeatherImageModel
import com.weather.forecast.clearsky.model.WeatherModel
import com.weather.forecast.clearsky.network.WeatherApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApiService: WeatherApiService) {
    suspend fun getWeatherData(weatherAPIKey: String, city:String): WeatherModel? {
        return weatherApiService.getWeatherData(weatherAPIKey, city)
    }

    suspend fun getWeatherImageData(city:String, condition: String): WeatherImageModel? {
        return weatherApiService.getWeatherImageData(city, condition)
    }
}