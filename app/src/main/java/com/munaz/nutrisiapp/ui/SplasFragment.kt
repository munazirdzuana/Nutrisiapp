package com.munaz.nutrisiapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.ui.register.VMRegist
import retrofit2.http.Tag
import android.content.ContentValues.TAG
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SplasFragment : Fragment() {
    private lateinit var viewModel: VMss
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splas, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMss::class.java]
        viewModel.token.observe(viewLifecycleOwner, Observer {
            val token = runBlocking { it.first() }
            if (token==null){
                findNavController().navigate(R.id.action_splasFragment_to_welcomeFragment)
            }
            else{
                Log.d(TAG, "token $it")
                findNavController().navigate(R.id.action_splasFragment_to_homeFragment)
            }
        })
        Handler(Looper.myLooper()!!).postDelayed({viewModel.doGetToken()},3000)
        return view
    }
}