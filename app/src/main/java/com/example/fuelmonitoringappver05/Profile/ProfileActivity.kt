package com.example.fuelmonitoringappver05.Profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fuelmonitoringappver05.HomeActivity
import com.example.fuelmonitoringappver05.databinding.ActivityProfileBinding
import com.example.fuelmonitoringappver05.firebaseDb.ProfileDao
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    var dao = ProfileDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBtnSave.setOnClickListener(){
                binding.etFullname.text.toString()
                binding.etMobile.text.toString()
                binding.etEmail.text.toString()
                binding.etVehicleName.text.toString()
                binding.etVehicleModel.text.toString()
                binding.etVehiclePlate.text.toString()


            Toast.makeText(applicationContext,"Success!",Toast.LENGTH_SHORT).show()
        }

        binding.imgBtnView.setOnClickListener(){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.imgBtnUpdate.setOnClickListener(){
            updateData()
        }

        binding.imgBtnDelete.setOnClickListener(){
            deleteData()
        }
        binding.imgProfilePhoto.setOnClickListener() {
            showCameraProfile()
        }

        binding.imgVehiclePhoto.setOnClickListener() {
            showCameraVechile()
        }
    }

    private fun deleteData() {
        dao.remove("")
    }

    private fun updateData() {
        var mapData = mutableMapOf<String, String>()
        mapData["fullName"] = binding.etFullname.text.toString()
        mapData["mobile"] = binding.etFullname.text.toString()
        mapData["email"] = binding.etFullname.text.toString()
        mapData["vehicleName"] = binding.etFullname.text.toString()
        mapData["vehicleModel"] = binding.etFullname.text.toString()
        mapData["vehiclePlate"] = binding.etFullname.text.toString()

        dao.update("fullName",mapData)

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
