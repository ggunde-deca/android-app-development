package com.weather.forecast.clearsky.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.weather.forecast.clearsky.model.WeatherImageModel
import com.weather.forecast.clearsky.model.WeatherModel
import com.weather.forecast.clearsky.network.ResultData
import com.weather.forecast.clearsky.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: WeatherUseCase) : ViewModel() {
    fun getWeatherData(weatherAPIKey: String, city: String): LiveData<ResultData<WeatherModel>> {
        return useCase.getWeatherData(weatherAPIKey, city).asLiveData()
    }

    fun getWeatherImage(city: String, condition: String): LiveData<ResultData<WeatherImageModel>> {
        return useCase.getWeatherImageData(city, condition).asLiveData()
    }
}