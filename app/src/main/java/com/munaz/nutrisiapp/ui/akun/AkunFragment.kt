package com.munaz.nutrisiapp.ui.akun

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.ModelPreferences
import com.munaz.nutrisiapp.databinding.FragmentAkunBinding
import com.munaz.nutrisiapp.ui.camera.VMcam
import com.munaz.nutrisiapp.ui.home.VMHome
import kotlin.math.log

class AkunFragment : Fragment() {
    private var _binding : FragmentAkunBinding?=null
    private val binding get()=_binding!!

    private lateinit var viewModel: VMHome
    private var logout:Boolean=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMHome::class.java]

        viewModel.profile.observe(viewLifecycleOwner, Observer {
            hadleProfile(it)
        })
        viewModel.logout.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoadingView()
                is Resource.Success -> {
                    it.data?.let{bole->
                        if (logout&&bole){
                            Toast.makeText(requireContext(), "token berhasil dihapus ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                is Resource.DataError-> {
                    showResult()
//                    Toast
                }
            }

        }

        binding.logout.setOnClickListener {
            showAlert()
        }

        return binding.root
    }

    private fun showAlert() {
        showResult()
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Logout")
            setMessage("Yakin Logout")
            setPositiveButton("logout") { dialog, _ ->
                dialog.dismiss()
                viewModel.dologout()
                findNavController().navigate(R.id.action_akunFragment_to_welcomeFragment)
            }
            setNegativeButton("kembali"){ dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }


    private fun hadleProfile(resource: Resource<ModelPreferences>) {
        when (resource) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                resource.data?.let {
                    showResult()
                    binding.tvName.text=it.name
                    binding.ivUmur.text= it.usia_user.toString()
                    binding.tvEmail.text=it.email
                    binding.aBbadan.text=it.beratBadan.toString()
                    binding.aTbadan.text=it.tinggiBadan.toString()
                    binding.aAlergi.text=it.alergi
                    binding.aRpenyakit.text=it.penyakit
                    binding.aStress.text=it.stress
                    binding.aAktivitas.text=it.aktivitas
                }
            }
            is Resource.DataError -> {
                showResult()
//                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

    private fun showResult() {
        binding.progressBar6.visibility=View.GONE

    }

    private fun showLoadingView() {
        binding.progressBar6.visibility=View.VISIBLE
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}