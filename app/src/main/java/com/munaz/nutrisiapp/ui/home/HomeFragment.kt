package com.munaz.nutrisiapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munaz.nutrisiapp.Model
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var rvAtic: RecyclerView
    private lateinit var rvResep: RecyclerView
    private val list = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        rvAtic = binding.rvArtikel
        rvResep=binding.rvResep
        list.addAll(getList())
        showArtikelList()
        showResepList()
        binding.btScan.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cameraFragment)
        }
        return binding.root
    }

    private fun showResepList() {
        rvResep.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listNetAdapter = RvAdapter(list)
        rvResep.adapter = listNetAdapter
        listNetAdapter.setOnItemClickCallback(object : RvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Model) {
                Toast.makeText(requireContext(), "Kamu memilih " + data.title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showArtikelList() {
        rvAtic.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listNetAdapter = RvAdapter(list)
        rvAtic.adapter = listNetAdapter
        listNetAdapter.setOnItemClickCallback(object : RvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Model) {
                Toast.makeText(requireContext(), "Kamu memilih " + data.title, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getList(): ArrayList<Model> {
        val dataTitle=resources.getStringArray(R.array.data_title)
        val datadesc=resources.getStringArray(R.array.data_desc)
        val dataList=ArrayList<Model>()
        for (i in dataTitle.indices){
            val net=Model(dataTitle[i],datadesc[i])
            dataList.add(net)
        }
        return dataList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
    }
}