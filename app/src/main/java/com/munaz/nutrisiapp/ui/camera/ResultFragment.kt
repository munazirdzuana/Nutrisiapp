package com.munaz.nutrisiapp.ui.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentCameraBinding
import com.munaz.nutrisiapp.databinding.FragmentResultBinding
import com.munaz.nutrisiapp.ui.home.HomeFragment


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.backButton3.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
        binding.predict.text= arguments?.getString(HomeFragment.ARTIKEL) ?:""
        val img= arguments?.getString(HomeFragment.IMG) ?:""

        Glide
            .with(requireContext())
            .load(img)
            .centerCrop()
            .placeholder(R.drawable.logo)
            .into(binding.ivScanfood);

        return binding.root
    }
}