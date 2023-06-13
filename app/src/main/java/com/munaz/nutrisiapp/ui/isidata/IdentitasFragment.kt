package com.munaz.nutrisiapp.ui.isidata

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentIdentitasBinding
import java.util.Calendar

class IdentitasFragment : Fragment() {
    private var _binding : FragmentIdentitasBinding? =null
    private val binding get()=_binding!!

//    private lateinit var selectDate:TextView
//    private lateinit var calcuAge:Button
//    private lateinit var showAge:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdentitasBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.next.setOnClickListener{
            findNavController().navigate(R.id.action_identitasFragment_to_budgetFragment)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

//    private fun selectDate(view: View) {
//        var c = Calendar.getInstance()
//        var cDay = c.get(Calendar.DAY_OF_MONTH)
//        var cMonth = c.get(Calendar.MONTH)
//        var cYear = c.get(Calendar.YEAR)
//
//        val calendarDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                cDay = dayOfMonth
//                cMonth = month
//                cYear = year
//                calcuAge.visibility = View.VISIBLE
//                selectDate.setOnClickListener {
//                    val currentYear = Calendar.getInstance()
//                        .get(Calendar.YEAR)
//                    val age = currentYear - cYear
//                    showAge.visibility = View.VISIBLE
//                    showAge.text = "Usiamu adalah $age tahun"
//                }
//                selectDate.text = "Tanggal lahirmu: $cDay/${cMonth+1}/$cYear"
//            },cYear,cMonth,cDay)
//        calendarDialog.show()
//    }

}