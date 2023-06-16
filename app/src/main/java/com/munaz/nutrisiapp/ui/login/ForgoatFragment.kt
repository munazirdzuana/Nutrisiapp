package com.munaz.nutrisiapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentForgoatBinding
import com.munaz.nutrisiapp.databinding.FragmentLoginBinding


class ForgoatFragment : Fragment() {
    private var _binding:FragmentForgoatBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgoatBinding.inflate(inflater, container, false)
        binding.loginn.setOnClickListener {
            findNavController().navigate(R.id.action_forgoatFragment_to_loginFragment)
        }
        binding.btLogin.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Berhasil !")
                setMessage("Periksa Email Anda jika benar")
                setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}