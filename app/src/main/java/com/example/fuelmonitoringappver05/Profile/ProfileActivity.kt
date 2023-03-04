package com.example.fuelmonitoringappver05.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fuelmonitoringappver05.databinding.ActivityProfileBinding
import com.example.fuelmonitoringappver05.firebaseDb.ProfileDao
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    var dao = ProfileDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBtnSave.setOnClickListener(){
            dao.add(Profile(
                binding.etFullname.text.toString(),
                binding.etMobile.text.toString(),
                binding.etEmail.text.toString(),
                binding.etVehicleName.text.toString(),
                binding.etVehicleModel.text.toString(),
                binding.etVehiclePlate.text.toString()))


            Toast.makeText(applicationContext,"Success!",Toast.LENGTH_SHORT).show()
        }

        binding.imgBtnView.setOnClickListener(){
            view()
        }

        binding.imgBtnUpdate.setOnClickListener(){
            updateData()
        }

        binding.imgBtnDelete.setOnClickListener(){
            deleteData()
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
    private fun view() {
        dao.get().addValueEventListener(object:ValueEventListener {
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
                }
                Toast.makeText(applicationContext,""+profiles,Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
            }


        })
    }
}
