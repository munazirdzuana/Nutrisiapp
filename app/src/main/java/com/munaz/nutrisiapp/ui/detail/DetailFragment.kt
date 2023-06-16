package com.munaz.nutrisiapp.ui.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentDetailBinding
import com.munaz.nutrisiapp.ui.home.HomeFragment
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.FAT
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.KAL
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.KAR
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.PRI
import com.munaz.nutrisiapp.ui.home.HomeFragment.Companion.PRO


class DetailFragment : Fragment() {
    private var _binding :FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private var isFavorite: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentDetailBinding.inflate(inflater, container, false)
        binding.descDetail.movementMethod = ScrollingMovementMethod()
        val img= arguments?.getString(HomeFragment.IMG) ?:""

        Glide
            .with(requireContext())
            .load(img)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.tvFood);

        binding.nmMakanan.text= arguments?.getString(HomeFragment.ARTIKEL) ?:""
        binding.descDetail.text= arguments?.getString(HomeFragment.DESC) ?:""
        binding.hrmakanan.text=arguments?.getInt(PRI).toString()
        binding.aKalori.text=arguments?.getInt(KAL).toString()
        binding.aKarbo.text=arguments?.getInt(KAR).toString()
        binding.aProtein.text=arguments?.getInt(PRO).toString()
        binding.aOil.text=arguments?.getInt(FAT).toString()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btFav.setOnClickListener {
            isFavorite = !isFavorite
            Toast.makeText(requireContext(), "in development" , Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    fun fav(){
        val favoriteIcon = if (isFavorite) R.drawable.ic_favorite_24 else R.drawable.ic_unfavorite
        binding.btFav.setImageResource(favoriteIcon)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}