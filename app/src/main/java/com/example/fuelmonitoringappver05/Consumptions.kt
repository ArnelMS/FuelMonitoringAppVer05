package com.example.fuelmonitoringappver05

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Consumptions (
    var kmPerLtr: String,
    var totalAmount: String,
    var millage: String,
    var pricePerLtr: String,
    var numberOfLiter: String,
    var gasType: String,
    var gasStation: String,
    var branch: String,
    var date: String
    ){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

