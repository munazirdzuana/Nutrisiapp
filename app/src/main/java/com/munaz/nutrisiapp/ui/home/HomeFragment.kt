package com.munaz.nutrisiapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.response.*
import com.munaz.nutrisiapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var Rvartikel: RecyclerView
    private lateinit var RvResep: RecyclerView
    private lateinit var viewModel: VMHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMHome::class.java]
        viewModel.responListArtikel.observe(viewLifecycleOwner, Observer {
            hadleArtikellist(it)
        })
        viewModel.responfood.observe(viewLifecycleOwner, Observer {
            hadleFoodRecipe(it)
        })
        viewModel.getArtikel()
        viewModel.getFoodResep(1,15)
        Rvartikel = binding.rvArtikel
        RvResep = binding.rvResep

        binding.btScan.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cameraFragment)
        }
        return binding.root
    }

    private fun hadleArtikellist(it: Resource<ArtikelResponse>) {
        when (it) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                it.data?.let {
                    showArikelList( it.data)
                     }
            }
            is Resource.DataError -> {
                showResult()
//                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

    private fun hadleFoodRecipe(it: Resource<ResepResponse>) {
        when (it) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                it.data?.let {
                    showResepList(it.foods)
                }
            }
            is Resource.DataError -> {
                showResult()
//                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

    private fun showLoadingView() {
        binding.progressBar3.visibility=View.VISIBLE
    }
    private fun showResult() {
        binding.progressBar3.visibility=View.GONE
    }

    private fun showArikelList(list:List<DataItem>) {
        showResult()
        Rvartikel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listNetAdapter = RvAdapter(list)
        Rvartikel.adapter = listNetAdapter
        listNetAdapter.setOnItemClickCallback(object : RvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val img=data.image
                val titel=data.title
                val desc = data.description
                val bundle = bundleOf(IMG to img, ARTIKEL to titel, DESC to desc)
                findNavController().navigate(R.id.action_homeFragment_to_detailArikel_Fragment, bundle)
                Toast.makeText(requireContext(), "Kamu memilih " + data.title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showResepList(list: List<FoodsItem>) {
        showResult()
        RvResep.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listNetAdapter = RvAdapterResep(list)
        RvResep.adapter = listNetAdapter
        listNetAdapter.setOnItemClickCallback(object : RvAdapterResep.OnItemClickCallback {
            override fun onItemClicked(data: FoodsItem) {
                val img=data.foodRecipe[0].image
                val titel=data.foodRecipe[0].name
                val desc = data.foodRecipe[0].description
                val bundle = bundleOf(IMG to img, ARTIKEL to titel, DESC to desc)
                findNavController().navigate(R.id.action_homeFragment_to_detailArikel_Fragment, bundle)
                Toast.makeText(requireContext(), "Kamu memilih " + data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
        const val ARTIKEL ="artikel"
        const val IMG ="img"
        const val DESC ="desc"
    }
}