package com.weather.forecast.clearsky.mainscreen.ui

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.weather.forecast.clearsky.databinding.ActivityMainBinding
import com.weather.forecast.clearsky.mainscreen.viewmodel.MainViewModel
import com.weather.forecast.clearsky.model.WeatherImageModel
import com.weather.forecast.clearsky.model.WeatherModel
import com.weather.forecast.clearsky.network.ResultData
import com.weather.forecast.clearsky.ui.theme.ClearSkyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.textView.setOnEditorActionListener { v, actionId, _ ->
            viewModel.getWeatherData(v.text.toString())
                .observe(this, getWeatherDataObserver(v.text.toString()))
            return@setOnEditorActionListener false
        }
        binding.progressBar.isVisible = false
        setContentView(binding.root)
    }

    private fun setImageUsingGlide(binding: ActivityMainBinding, imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .into(binding.imageView);
    }

    private fun getWeatherDataObserver (v: String): Observer<ResultData<WeatherModel>> {
        return Observer<ResultData<WeatherModel>> ()  {
            when (it) {
                is ResultData.Success -> {
                    Log.i("harry", "" + it.toString())
                    it.data?.let { it1 -> Log.d("TAG", "onCreate: $it1") }
                    binding.temperature.text = it.data!!.current.temp_f.toString() + " F"
                    binding.windSpeed.text = it.data!!.current.wind_mph.toString() + " mph"
                    binding.humidity.text = it.data!!.current.humidity.toString() + " %"
                    viewModel.getWeatherImage(v, it.data!!.current.condition.text).observe(this, getWeatherImageObserver())
                }

                is ResultData.Failed -> {
                    Log.d("TAG", "onCreate: failed ${it.message}")
                }

                is ResultData.Loading -> {
                    Log.d("TAG", "onCreate: Loading")
                    binding.temperature.text = ""
                    binding.windSpeed.text = ""
                    binding.humidity.text = ""
                }
            }
        }
    }
    private fun getWeatherImageObserver (): Observer<ResultData<WeatherImageModel>> {
        return Observer<ResultData<WeatherImageModel>> () { itimage ->
            when (itimage) {
                is ResultData.Success -> {
                    Log.i("harry", "onCreateImage: $itimage")
                    itimage.data?.let { it1 -> Log.d("TAG", "onCreate: $it1") }
                    // binding.textView.setText(itimage.toString())
                    setImageUsingGlide(binding, itimage.data!!.image_url)
                    binding.progressBar.isVisible = false
                }

                is ResultData.Failed -> {
                    Log.d("TAG", "onCreateImage: failed ${itimage.message}")
                }

                is ResultData.Loading -> {
                    Log.d("TAG", "onCreateImage: Loading")
                    binding.imageView.setImageDrawable(null)
                    binding.progressBar.isVisible = true
                }
            }
        }
    }
}