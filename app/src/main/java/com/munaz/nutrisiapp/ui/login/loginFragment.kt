package com.munaz.nutrisiapp.ui.login

import android.content.ContentValues
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
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
import com.munaz.nutrisiapp.data.request.LoginReq
import com.munaz.nutrisiapp.data.response.LoginResponse
import com.munaz.nutrisiapp.databinding.FragmentLoginBinding
import com.munaz.nutrisiapp.ui.register.VMRegist
import dagger.hilt.android.AndroidEntryPoint


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: VMLogin
    var saved : Boolean =false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMLogin::class.java]
        val view = binding.root
        viewModel.response.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
        viewModel.showErr.observe(viewLifecycleOwner, Observer {
            handleErr(it)
        })
        viewModel.savtoken.observe(viewLifecycleOwner, Observer { booleanResource ->
            saved = when (booleanResource) {
                is Resource.Success -> true
                else -> {
                    showResult()
                    booleanResource.errorCode?.let { viewModel.showErrMessage(it) }
                    false
                }
            }
        })
        binding.regis.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.bLogin.setOnClickListener {
            val mail =binding.etEmail.text.toString()
            val password =binding.etPassword.text.toString()
            val data= LoginReq(mail, password)
            viewModel.doLogin(data)
        }
        return view
    }

    private fun handleResponse(it: Resource<LoginResponse>) {
        when (it) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                it.data?.let { showAlert(it) }
            }
            is Resource.DataError -> {
                showResult()
                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

    private fun showResult() {
        binding.progressBar2.visibility=View.GONE
    }

    private fun handleErr(it: String) {
        binding.err2.visibility = View.VISIBLE
        binding.err2.text= it
    }

    private fun showAlert(respons: LoginResponse) {
        showResult()
        viewModel.doSaveToken()
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Berhasil !")
            setMessage(respons.message)
            setPositiveButton("Lanjut") { dialog, _ ->
                if (saved){
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }else{
                    dialog.dismiss()
                }
            }
            create()
            show()
        }
    }

    private fun showLoadingView() {
        binding.progressBar2.visibility=View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {

    }
}