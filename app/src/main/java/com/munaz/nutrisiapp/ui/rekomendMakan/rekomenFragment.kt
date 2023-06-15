package com.munaz.nutrisiapp.ui.rekomendMakan

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.local.ModelPreferences
import com.munaz.nutrisiapp.data.request.RekomendasiReq
import com.munaz.nutrisiapp.data.response.*
import com.munaz.nutrisiapp.databinding.FragmentRegisterBinding
import com.munaz.nutrisiapp.databinding.FragmentRekomenBinding
import com.munaz.nutrisiapp.ui.home.VMHome
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class rekomenFragment : Fragment() {
    private var _binding: FragmentRekomenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: VMHome
    private lateinit var profile:ModelPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRekomenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMHome::class.java]
        viewModel.profile.observe(viewLifecycleOwner){
            hadleProfile(it)
        }
        viewModel.responseRekomendasi.observe(viewLifecycleOwner){
            handleRecomen(it)
        }
        binding.btGenerate.setOnClickListener {
            val umur= profile.usia_user
            val berat=profile.beratBadan
            val tinggi=profile.tinggiBadan
            val jenisK=profile.jenisKelamin
            val riwayat=profile.penyakit
            val alergi=profile.alergi
            val aktivitas=profile.aktivitas
            val stres=profile.stress
            Log.d(TAG, "hasil = $umur,$berat,$tinggi,$jenisK,$riwayat,$alergi,$aktivitas,$stres")
            val data=RekomendasiReq(umur,berat,tinggi,jenisK,riwayat,alergi,aktivitas,stres, dislike_food = "")
            viewModel.getRecomendasi(data)
        }
        return binding.root
    }

    private fun handleRecomen(it: Resource<RecomendasiResponseX>) {
        when (it) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                it.data?.let { showReco(it) }
            }
            is Resource.DataError -> {
                showResult()
//                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }

    }

    private fun showReco(it: RecomendasiResponseX) {
        showPagi(it.datas[0].breakfast)
        showSiang(it.datas[1].lunch)
        showMalam(it.datas[2].dinner)
        showSnackPagi(it.datas[3].snackpagi)
        showsnackSore(it.datas[4].snacksiang)
        showResult()
    }

    private fun showsnackSore(snacksiang: Snacksiang) {
        Glide
            .with(requireContext())
            .load(snacksiang.image)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.ivFood1);
        binding.tvmakanan5.text=snacksiang.name
        binding.tvHarga5.text=snacksiang.price.toString()
        binding.tv2Kalori5.text=snacksiang.calories.toString()
        binding.tv2Karbo5.text=snacksiang.carbohidrat.toString()
        binding.tvFat5.text=snacksiang.fat.toString()
        binding.tvProtein5.text=snacksiang.fat.toString()
    }

    private fun showSnackPagi(snackpagi: Snackpagi) {
        Glide
            .with(requireContext())
            .load(snackpagi.image)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.ivFood1);
        binding.tvmakanan4.text=snackpagi.name
        binding.tvHarga4.text=snackpagi.price.toString()
        binding.tv2Kalori4.text=snackpagi.calories.toString()
        binding.tv2Karbo4.text=snackpagi.carbohidrat.toString()
        binding.tvFat4.text=snackpagi.fat.toString()
        binding.tvProtein4.text=snackpagi.fat.toString()
    }

    private fun showMalam(dinner: Dinner) {
        Glide
            .with(requireContext())
            .load(dinner.image)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.ivFood1);
        binding.tvmakanan3.text=dinner.name
        binding.tvHarga3.text=dinner.price.toString()
        binding.tv2Kalori3.text=dinner.calories.toString()
        binding.tv2Karbo3.text=dinner.carbohidrat.toString()
        binding.tvFat3.text=dinner.fat.toString()
        binding.tvProtein3.text=dinner.fat.toString()
    }

    private fun showSiang(lunch: Lunch) {
        Glide
            .with(requireContext())
            .load(lunch.image)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.ivFood1);
        binding.tvmakanan2.text=lunch.name
        binding.tvHarga2.text=lunch.price.toString()
        binding.tv2Kalori2.text=lunch.calories.toString()
        binding.tv2Karbo2.text=lunch.carbohidrat.toString()
        binding.tvFat2.text=lunch.fat.toString()
        binding.tvProtein2.text=lunch.fat.toString()
    }

    private fun showPagi(breakfast: Breakfast) {
        Glide
            .with(requireContext())
            .load(breakfast.image)
            .centerCrop()
            .placeholder(R.drawable.doge)
            .into(binding.ivFood1);
        binding.tvmakanan1.text=breakfast.name
        binding.tvHarga1.text=breakfast.price.toString()
        binding.tv2Kalori1.text=breakfast.calories.toString()
        binding.tv2Karbo1.text=breakfast.carbohidrat.toString()
        binding.tvFat1.text=breakfast.fat.toString()
        binding.tvProtein1.text=breakfast.fat.toString()
    }

    private fun showLoadingView() {
        binding.progressBar5.visibility=View.VISIBLE
    }
    private fun showResult() {
        binding.progressBar5.visibility=View.GONE
        binding.sv.visibility=View.VISIBLE
    }


    private fun hadleProfile(it: Resource<ModelPreferences>) {
        when (it) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                it.data?.let {
                    showResult()
                    profile=it
                    binding.tvName.text=it.email
                }
            }
            is Resource.DataError -> {
                showResult()
//                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

}