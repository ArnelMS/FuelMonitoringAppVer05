package com.example.fuelmonitoringappver05

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fuelmonitoringappver05.databinding.ActivityHomeBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgProfilePhoto.setOnClickListener() {
            showCameraProfile()
        }

        binding.imgVehiclePhoto.setOnClickListener() {
            showCameraVechile()
        }

    }

    private fun showCameraProfile() {
        Dexter.withContext(this).withPermission(
            Manifest.permission.CAMERA
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivity(cameraIntent) // only used to access camera

                cameraLauncher1.launch(cameraIntent)
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()

//                cameraLauncher2.launch(cameraIntent)
//                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }

    val cameraLauncher1 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras.let {
                    val image1: Bitmap = result.data?.extras?.get("data") as Bitmap
                    binding.imgProfilePhoto.setImageBitmap(image1)
                }
            }
        }


private fun showCameraVechile() {
    Dexter.withContext(this).withPermission(
        Manifest.permission.CAMERA
    ).withListener(object : PermissionListener {
        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivity(cameraIntent) // only used to access camera

            cameraLauncher.launch(cameraIntent)
            Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()

        }

        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
            Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
        }

        override fun onPermissionRationaleShouldBeShown(
            request: PermissionRequest?,
            token: PermissionToken?
        ) {
            token?.continuePermissionRequest()
        }

    }).onSameThread().check()
}

    val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras.let {
                    val image2: Bitmap = result.data?.extras?.get("data") as Bitmap
                    binding.imgVehiclePhoto.setImageBitmap(image2)
                }
            }
        }
}