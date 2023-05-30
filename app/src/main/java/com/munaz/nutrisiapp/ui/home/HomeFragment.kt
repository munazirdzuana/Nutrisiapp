package com.munaz.nutrisiapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munaz.nutrisiapp.Model
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var rvAtic: RecyclerView
    private val list = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        rvAtic = binding.rvHome
        list.addAll(getList())
        showRecyclerList()
        return binding.root
    }

    private fun showRecyclerList() {
        rvAtic.layoutManager = LinearLayoutManager(requireContext())
        val listNetAdapter = RvAdapter(list)
        rvAtic.adapter = listNetAdapter
        listNetAdapter.setOnItemClickCallback(object : RvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Model) {
                Toast.makeText(requireContext(), "Kamu memilih " + data.title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showSelectedHero(data: Model) {

    }

    private fun getList(): ArrayList<Model> {
        val dataTitle=resources.getStringArray(R.array.data_title)
        val dataRating=resources.getStringArray(R.array.data_desc)
        val dataList=ArrayList<Model>()
        for (i in dataTitle.indices){
            val net=Model(dataTitle[i], dataRating[i])
            dataList.add(net)
        }
        return dataList
    }

    companion object {
    }
}