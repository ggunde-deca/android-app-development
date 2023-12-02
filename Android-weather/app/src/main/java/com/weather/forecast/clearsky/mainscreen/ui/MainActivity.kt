package com.weather.forecast.clearsky.mainscreen.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.weather.forecast.clearsky.databinding.ActivityMainBinding
import com.weather.forecast.clearsky.mainscreen.viewmodel.MainViewModel
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

        binding.textView.setOnEditorActionListener { v, actionId, event ->
            viewModel.getWeatherData(v.toString()).observe(this) {
                when (it) {
                    is ResultData.Success -> {
                        Log.i("harry", "" + it.toString())
                        it.data?.let { it1 -> Log.d("TAG", "onCreate: $it1") }
                        viewModel.getWeatherImage(v.text.toString(), it.data!!.current.condition.text).observe(this) { itimage ->
                            when (itimage) {
                                is ResultData.Success -> {
                                    Log.i("harry", "onCreateImage: $itimage")
                                    itimage.data?.let { it1 -> Log.d("TAG", "onCreate: $it1") }
                                    // binding.textView.setText(itimage.toString())
                                    setImageUsingGlide(binding, itimage.data!!.image_url)
                                }

                                is ResultData.Failed -> {
                                    Log.d("TAG", "onCreateImage: failed ${itimage.message}")
                                }

                                is ResultData.Loading -> {
                                    Log.d("TAG", "onCreateImage: Loading")
                                }
                            }
                        }
                    }

                    is ResultData.Failed -> {
                        Log.d("TAG", "onCreate: failed ${it.message}")
                    }

                    is ResultData.Loading -> {

                        Log.d("TAG", "onCreate: Loading")
                    }
                }
            }

            return@setOnEditorActionListener true
        }
        setContentView(binding.root)

//        setContent {
//            ClearSkyTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }

    private fun setImageUsingGlide(binding: ActivityMainBinding, imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .into(binding.imageView);
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClearSkyTheme {
        Greeting("Android")
    }
}