package com.munaz.nutrisiapp.ui.favMakanan

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
import com.munaz.nutrisiapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvFav: RecyclerView
    private val list = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        rvFav = binding.rvFav
        list.addAll(getList())
        showFav()
        return binding.root
    }

    private fun showFav() {
        rvFav.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val listAdapter=FavRvAdapter(list)
        rvFav.adapter=listAdapter
        listAdapter.setOnItemClickCallback(object :FavRvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Model) {
                Toast.makeText(requireContext(), "Kamu memilih " + data.title, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
            }
        })
    }

    private fun getList(): ArrayList<Model> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val datadesc = resources.getStringArray(R.array.data_desc)
        val dataList = ArrayList<Model>()
        for (i in dataTitle.indices) {
            val net = Model(dataTitle[i], datadesc[i])
            dataList.add(net)
        }
        return dataList
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}