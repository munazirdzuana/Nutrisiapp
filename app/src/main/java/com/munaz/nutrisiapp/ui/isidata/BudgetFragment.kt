package com.munaz.nutrisiapp.ui.isidata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentBudgetBinding

class BudgetFragment : Fragment() {
    private var _binding : FragmentBudgetBinding? =null
    private val binding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.next.setOnClickListener{
            findNavController().navigate(R.id.action_budgetFragment_to_riwayatFragment)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {

    }
}