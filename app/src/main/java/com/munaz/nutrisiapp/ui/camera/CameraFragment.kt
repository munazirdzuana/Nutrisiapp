package com.munaz.nutrisiapp.ui.camera

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
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
import androidx.navigation.fragment.findNavController
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.databinding.FragmentCameraBinding
import com.munaz.nutrisiapp.utils.createCustomTempFile
import com.munaz.nutrisiapp.utils.uriToFile
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import java.io.File

class CameraFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var getFile: File? = null
    lateinit var currentPhotoPath :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        requestLocationPermission()
        if (!hasCamPermission()) {
            Toast.makeText(
                requireContext(),
                "gagal bang!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
        binding.backButton2.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btCam.setOnClickListener {
            val intentCam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intentCam.resolveActivity(requireActivity().packageManager)!=null) {
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
            val intentGaleri=Intent()
            intentGaleri.action=ACTION_GET_CONTENT
            intentGaleri.type="image/*"
            val choosed=Intent.createChooser(intentGaleri,"pilih foto")
            launcherIntentGallery.launch(choosed)
        }

        return binding.root
    }

    private fun hasCamPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without Location Permission.",
            REQUEST_code,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val file=File(currentPhotoPath)
            file.let {
                getFile=it
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
                getFile=myFile
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