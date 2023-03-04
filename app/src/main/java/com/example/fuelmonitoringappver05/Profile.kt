package com.example.fuelmonitoringappver05

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (
    var fullName: String,
    var mobile: String,
    var email: String,
    var vehicleName: String,
    var vehicleModel: String,
    var vehiclePlate: String
){
    @PrimaryKey
    var id : Int = 0
}