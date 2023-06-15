package com.munaz.nutrisiapp.ui.isidata

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.ModelPreferences
import com.munaz.nutrisiapp.databinding.FragmentRiwayatBinding
import com.munaz.nutrisiapp.ui.isidata.IdentitasFragment.Companion.BBADAN
import com.munaz.nutrisiapp.ui.isidata.IdentitasFragment.Companion.JENISKEL
import com.munaz.nutrisiapp.ui.isidata.IdentitasFragment.Companion.TANGGALL
import com.munaz.nutrisiapp.ui.isidata.IdentitasFragment.Companion.TBADAN
import com.munaz.nutrisiapp.ui.login.VMLogin
import com.munaz.nutrisiapp.ui.register.RegisterFragment.Companion.EMAILL
import com.munaz.nutrisiapp.ui.register.RegisterFragment.Companion.NAMEE

class RiwayatFragment : Fragment() {
    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: VMRiwayat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(requireActivity())[VMRiwayat::class.java]

        binding.submit.setOnClickListener {
            val selectedItemAle = binding.typeAllergy.selectedItem as String
            val selectedItemDis = binding.typeDisease.selectedItem as String
            val selectedItemStr = binding.typeStress.selectedItem as String
            val selectedItemAct = binding.typeActivity.selectedItem as String
            val nama = arguments?.getString(NAMEE) ?: ""
            val email = arguments?.getString(EMAILL) ?: ""
            val jenisk = arguments?.getString(JENISKEL) ?: ""
            val umur = arguments?.getInt(TANGGALL) ?: 0
            val berat=arguments?.getInt(BBADAN)?:0
            val tinggi=arguments?.getInt(TBADAN)?:0
            val data = ModelPreferences(
                email,
                nama,
                umur,
                berat,
                tinggi,
                jenisk,
                selectedItemAle,
                selectedItemDis,
                selectedItemAct,
                selectedItemStr
            )
            Log.d(TAG,"simpan = $email,\n" +
                    "                $nama,\n" +
                    "               $umur,\n" +
                    "                $berat,\n" +
                    "                $tinggi,\n" +
                    "                $jenisk,\n" +
                    "                $selectedItemAle,\n" +
                    "                $selectedItemDis,\n" +
                    "                $selectedItemAct,\n" +
                    "                $selectedItemStr")
            viewModel.doSaveProfile(data)
         Toast.makeText(requireContext(), data.name , Toast.LENGTH_SHORT).show()
        }

        viewModel.responProfile.observe(viewLifecycleOwner){
            handleResponse(it)
        }
        return view
    }

    private fun handleResponse(it: Resource<Boolean>) {
        when (it) {
            is Resource.Success -> findNavController().navigate(R.id.action_riwayatFragment_to_homeFragment)
            else -> { Toast.makeText(requireContext(), it.toString() , Toast.LENGTH_SHORT).show() }
        }
    }
}