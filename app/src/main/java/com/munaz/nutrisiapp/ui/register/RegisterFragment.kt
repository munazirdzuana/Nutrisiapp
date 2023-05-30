package com.munaz.nutrisiapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentLoginBinding
import com.munaz.nutrisiapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding :FragmentRegisterBinding? =null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.toLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.creAkun.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
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