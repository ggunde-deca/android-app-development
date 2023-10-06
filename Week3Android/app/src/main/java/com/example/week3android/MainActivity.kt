package com.example.week3android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import com.example.week3android.databinding.ActivityMainBinding
import com.example.week3android.databinding.FragmentBinding

object data {

    var binding: FragmentBinding? = null

}
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val inflatedFragment = FragmentBinding.inflate(layoutInflater)
        data.binding = inflatedFragment

        inflatedFragment.user = ObservableField<String>()

        _binding.button.setOnClickListener {
            // fragmentBinding.user?.set("https://image.tmdb.org/t/p/original/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg")
            inflatedFragment.user!!.set("https://image.tmdb.org/t/p/original/" + _binding.textView.text)
        }
    }
}