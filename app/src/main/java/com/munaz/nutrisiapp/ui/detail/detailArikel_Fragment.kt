package com.munaz.nutrisiapp.ui.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.response.DataItem
import com.munaz.nutrisiapp.databinding.FragmentDetailArikelBinding
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.ARTIKEL
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.DESC
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.IMG

class detailArikel_Fragment : Fragment() {
    private var _binding: FragmentDetailArikelBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailArikelBinding.inflate(inflater, container, false)
        binding.descArtikel.movementMethod = ScrollingMovementMethod()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvTitle.text= arguments?.getString(ARTIKEL) ?:""
        val img= arguments?.getString(IMG) ?:""
        binding.descArtikel.text= arguments?.getString(DESC) ?:""

        Glide
            .with(requireContext())
            .load(img)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.ivFood);

        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}