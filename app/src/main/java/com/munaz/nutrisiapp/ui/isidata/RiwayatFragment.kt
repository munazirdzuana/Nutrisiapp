package com.munaz.nutrisiapp.ui.isidata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentRiwayatBinding

class RiwayatFragment : Fragment() {
    private var _binding : FragmentRiwayatBinding? =null
    private val binding get()=_binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
            val view = binding.root
            binding.submit.setOnClickListener {
                findNavController().navigate(R.id.action_riwayatFragment_to_homeFragment)
            }
            return view
        }
}