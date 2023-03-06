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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fuelmonitoringappver05.Profile.Profile
import com.example.fuelmonitoringappver05.Profile.ProfileAdapter
import com.example.fuelmonitoringappver05.databinding.ActivityHomeBinding
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    lateinit var adapter: ProfileAdapter
    var dao = ProfileDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        view()

    }

    private fun view() {
        dao.get().addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var profiles : ArrayList<Profile> = ArrayList<Profile>()

                var dataFromDb = snapshot.children
//                Toast.makeText(applicationContext,""+dataFromDb,Toast.LENGTH_SHORT).show()

                for(data in dataFromDb){
                    //get id of Object from DB
//                    var id = data.key.toString()

                    var fullName = data.child("fullName").value.toString()
                    var mobile = data.child("mobile").value.toString()
                    var email = data.child("email").value.toString()
                    var vehicleName = data.child("vehicleName").value.toString()
                    var vehicleModel = data.child("vehicleModel").value.toString()
                    var vehiclePlate = data.child("vehiclePlate").value.toString()

                    var profile = Profile(fullName, mobile, email, vehicleName, vehicleModel, vehiclePlate)
                    profiles.add(profile)


                    binding.recyclerProfileView.adapter = adapter
                    binding.recyclerProfileView.layoutManager = LinearLayoutManager(applicationContext)


                }


                Toast.makeText(applicationContext,""+profiles,Toast.LENGTH_SHORT).show()
            }


            override fun onCancelled(error: DatabaseError) {
            }


        })
    }
}