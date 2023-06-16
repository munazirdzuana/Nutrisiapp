package com.munaz.nutrisiapp.ui.favMakanan

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
import com.munaz.nutrisiapp.Model
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.response.FoodsItem
import com.munaz.nutrisiapp.data.response.ResepResponse
import com.munaz.nutrisiapp.databinding.FragmentFavoriteBinding
import com.munaz.nutrisiapp.ui.home.HomeFragment
import com.munaz.nutrisiapp.ui.home.VMHome

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var RvResep: RecyclerView
    private lateinit var viewModel: VMHome
    private val list = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMHome::class.java]
        viewModel.responfood.observe(viewLifecycleOwner, Observer {
            hadleFoodRecipe(it)
        })
        RvResep = binding.rvFav

        return binding.root
    }

    private fun hadleFoodRecipe(it: Resource<ResepResponse>) {
        when (it) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                it.data?.let {
                    showResepList(it.foods)
                }
            }
            is Resource.DataError -> {
//                showResult()
//                it.errorCode?.let { viewModel.showErrMessage(it) }
            }
        }
    }

    private fun showResepList(foods: List<FoodsItem>) {
        RvResep.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val listNetAdapter = FavRvAdapter(foods)
        RvResep.adapter = listNetAdapter
        listNetAdapter.setOnItemClickCallback(object : FavRvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodsItem) {
                val img = data.foodRecipe[0].image
                val titel = data.foodRecipe[0].name
                val desc = data.foodRecipe[0].description
                val harga = data.price
                val kal = data.foodDetail[0].calories
                val kar = data.foodDetail[0].carbohidrat
                val fat = data.foodDetail[0].fat
                val pro = data.foodDetail[0].protein
                val bundle =
                    bundleOf(
                        HomeFragment.IMG to img,
                        HomeFragment.ARTIKEL to titel,
                        HomeFragment.DESC to desc,
                        HomeFragment.PRI to harga,
                        HomeFragment.KAL to kal,
                        HomeFragment.KAR to kar,
                        HomeFragment.FAT to fat,
                        HomeFragment.PRO to pro
                    )
                findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
                Toast.makeText(requireContext(), "Kamu memilih " + data.name, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}