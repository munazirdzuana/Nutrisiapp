package com.munaz.nutrisiapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.request.RegisReq
import com.munaz.nutrisiapp.data.response.LoginResponse
import com.munaz.nutrisiapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint



class RegisterFragment : Fragment() {
    private var _binding :FragmentRegisterBinding? =null
    private val binding get()=_binding!!

    private lateinit var viewModel:VMRegist

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[VMRegist::class.java]
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        viewModel.response.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
        viewModel.showErr.observe(viewLifecycleOwner, Observer {
            handleErr(it)
        })

        binding.toLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.creAkun.setOnClickListener {
            val name =binding.eNama.text.toString()
            val mail =binding.etEmail.text.toString()
            val password =binding.etPassword.text.toString()
            val rePassword =binding.rePassword.text.toString()
            val data= RegisReq(name, mail, password, rePassword)
            viewModel.doregis(data)
        }
        return binding.root
    }

    private fun handleErr(it: String) {
        showResult()
        binding.err.visibility = View.VISIBLE
        binding.err.text= it
    }

    private fun showLoadingView() {
        binding.progressBar.visibility=View.VISIBLE
    }
    private fun showResult() {
        binding.progressBar.visibility=View.GONE
    }

    private fun handleResponse(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { showAlert(recipes = it) }
            is Resource.DataError -> {
                status.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

    private fun showAlert(recipes: LoginResponse) {
        showResult()
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Berhasil !")
            setMessage(recipes.message)
            setPositiveButton("Lanjut") { dialog, _ ->
                dialog.dismiss()
                findNavController().navigate(R.id.action_registerFragment_to_identitasFragment)
            }
            create()
            show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {

    }
}