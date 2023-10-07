package com.google.fragmentstest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.fragmentstest.viewmodel.ImageViewModel
import com.google.fragmentstest.R
import com.google.fragmentstest.databinding.FragmentSecondBinding
import com.google.fragmentstest.viewmodel.getImageUsingRetrofit

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: ImageViewModel by activityViewModels() // why val because object forces us to amke it immutable

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val count = arguments?.getInt("key", 0)
        binding.textviewSecond.text = "Received Count " + count

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        // lifecycleowner -> provides a way to unsubscribe when we the app / page / fragment is not used
        viewModel.getImageUrl().observe(viewLifecycleOwner) {
            setImageUsingGlide(binding,getImageUsingRetrofit(it.toString())!!)
        }

        binding.btnDecrement.setOnClickListener {
            // viewModel.decrement()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setImageUsingGlide(binding: FragmentSecondBinding, imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .into(binding.imageView);
    }
}