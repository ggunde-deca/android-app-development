package com.example.week3android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.week3android.databinding.FragmentBinding


class ExampleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return data.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragmentBinding.user =  ObservableInt()
        data.binding!!.user!!.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                Log.i("Debug","Changed")
                val observableField = sender as ObservableField<String>
                observableField.get()?.let { setImageUsingGlide(data.binding!!, it) }
            }
        });
    }

    private fun setImageUsingGlide(binding: FragmentBinding, imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .into(binding.imageView);
    }
}


