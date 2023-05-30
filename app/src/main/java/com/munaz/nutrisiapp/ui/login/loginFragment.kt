package com.munaz.nutrisiapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentLoginBinding

class loginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.regis.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.bLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {

    }
}