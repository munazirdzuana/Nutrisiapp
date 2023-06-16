package com.munaz.nutrisiapp.ui.isidata

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentIdentitasBinding
import com.munaz.nutrisiapp.ui.home.HomeFragment
import com.munaz.nutrisiapp.ui.register.RegisterFragment.Companion.EMAILL
import com.munaz.nutrisiapp.ui.register.RegisterFragment.Companion.NAMEE
import java.text.SimpleDateFormat
import java.util.*

class IdentitasFragment : Fragment() {
    private var _binding: FragmentIdentitasBinding? = null
    private val binding get() = _binding!!

    private lateinit var JK: String
    private lateinit var lahir:Calendar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdentitasBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.next.setOnClickListener {
            HandleBUndle()
        }

        binding.buttonPilihTanggal.setOnClickListener {
            tampilkanDatePicker(requireContext(), binding.editTextTanggalLahir)
        }
        return view
    }

    fun HandleBUndle() {
        val nama =arguments?.getString(NAMEE) ?:""
        val email =arguments?.getString(EMAILL) ?:""
        val sberat  = binding.etWeight.text.toString()
        val berat =sberat.toInt()
        val stinggi =binding.etHeight.text.toString()
        val tinggi =stinggi.toInt()
        val jenisK=inputData()
        val umur = hitungUmur(lahir)
        val bundle =
            bundleOf(
                JENISKEL to jenisK ,
                TANGGALL to umur,
                BBADAN to berat,
                TBADAN to tinggi,
                NAMEE to nama,
                EMAILL to email
            )
        Toast.makeText(requireContext(), "$nama $email $berat $tinggi $jenisK $umur", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_identitasFragment_to_riwayatFragment, bundle)
    }

    private fun inputData(): String {
        val radioGroup = binding.JenisKelamin
        val selectedOption: Int = radioGroup.checkedRadioButtonId
        val radioButton: RadioButton = binding.root.findViewById(selectedOption)
        return when (radioButton.text) {
            "Pria" -> "L"
            "Wanita" -> "P"
            else -> {
                "P"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun tampilkanDatePicker(context: Context, editText: EditText) {
        val calendar = Calendar.getInstance()
        val tahun = calendar.get(Calendar.YEAR)
        val bulan = calendar.get(Calendar.MONTH)
        val hari = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog =
            DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                val tanggalLahir = Calendar.getInstance()
                tanggalLahir.set(selectedYear, selectedMonth, selectedDay)
                lahir=tanggalLahir
                val formattedDate = formatDate(tanggalLahir)
                editText.setText(formattedDate)
            }, tahun, bulan, hari)

        datePickerDialog.show()
    }


    fun formatDate(calendar: Calendar): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun hitungUmur(tanggalLahir: Calendar): Int {
        val sekarang = Calendar.getInstance()

        // Menghitung selisih tahun
        var selisihTahun = sekarang.get(Calendar.YEAR) - tanggalLahir.get(Calendar.YEAR)

        // Menghitung selisih bulan
        val bulanSekarang = sekarang.get(Calendar.MONTH)
        val bulanLahir = tanggalLahir.get(Calendar.MONTH)
        val selisihBulan = bulanSekarang - bulanLahir

        // Menghitung selisih hari
        val hariSekarang = sekarang.get(Calendar.DAY_OF_MONTH)
        val hariLahir = tanggalLahir.get(Calendar.DAY_OF_MONTH)
        val selisihHari = hariSekarang - hariLahir

        // Mengurangi 1 tahun jika belum lewat hari ulang tahun
        if (selisihBulan < 0 || (selisihBulan == 0 && selisihHari < 0)) {
            selisihTahun--
        }

        return selisihTahun
    }

    companion object {
        const val TANGGALL = "LAhir"
        const val JENISKEL = "kelamin"
        const val BBADAN = "BERATbadan"
        const val TBADAN = "TinggiBadan"
    }


}