package com.munaz.nutrisiapp.ui.camera

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.Resource
import com.munaz.nutrisiapp.data.response.ImageResponse
import com.munaz.nutrisiapp.databinding.FragmentCameraBinding
import com.munaz.nutrisiapp.ui.home.HomeFragment
import com.munaz.nutrisiapp.utils.createCustomTempFile
import com.munaz.nutrisiapp.utils.uriToFile
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import java.io.File

class CameraFragment : Fragment(){
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var getFile: File? = null
    private lateinit var viewModel: VMcam
    lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[VMcam::class.java]
        viewModel.responScan.observe(viewLifecycleOwner) {
            if (getFile != null) {
                hadleScanCam(it)
            }
        }
        binding.backButton2.setOnClickListener { findNavController().popBackStack() }
        binding.btScan.setOnClickListener {
            if (getFile != null) {
                viewModel.doImage(getFile!!)
            } else {
                Toast.makeText(requireContext(),"Masukan Gambar", Toast.LENGTH_LONG).show()
            }
        }



        binding.btCam.setOnClickListener {
            val intentCam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intentCam.resolveActivity(requireActivity().packageManager) != null) {
                val photoFile: File = createCustomTempFile(requireContext().applicationContext)
                val photoURI: Uri = FileProvider.getUriForFile(
                    requireContext(),
                    "com.munaz.nutrisiapp.ui.camera.CameraFragment",
                    photoFile
                )
                currentPhotoPath = photoFile.absolutePath
                intentCam.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                launcherIntentCamera.launch(intentCam)
            }
        }
        binding.btGaleri.setOnClickListener {
            val intentGaleri = Intent()
            intentGaleri.action = ACTION_GET_CONTENT
            intentGaleri.type = "image/*"
            val choosed = Intent.createChooser(intentGaleri, "pilih foto")
            launcherIntentGallery.launch(choosed)
        }

        return binding.root
    }

    private fun showLoadingView() {
        binding.progressBar4.visibility=View.VISIBLE
    }
    private fun showResult() {
        binding.progressBar4.visibility=View.GONE
    }

    private fun hadleScanCam(it: Resource<ImageResponse>) {
        when (it) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                it.data?.let {
                    val img=it.data.imageUrl
                    val titel=it.data.predict
                    val bundle = bundleOf(HomeFragment.IMG to img, HomeFragment.ARTIKEL to titel)
                    findNavController().navigate(R.id.action_cameraFragment_to_resultFragment, bundle,)
                }
            }
            is Resource.DataError -> {
                showResult()
                Toast.makeText(requireContext(),"error network", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val file = File(currentPhotoPath)
            file.let {
                getFile = it
                binding.prevImg.setImageBitmap(BitmapFactory.decodeFile(it.path))
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireContext())
                getFile = myFile
                binding.prevImg.setImageURI(uri)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val REQUEST_code = 1
    }

}