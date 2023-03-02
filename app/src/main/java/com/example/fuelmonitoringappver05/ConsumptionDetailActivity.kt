package com.example.fuelmonitoringappver05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fuelmonitoringappver05.databinding.ActivityConsumptionDetailBinding

lateinit var binding : ActivityConsumptionDetailBinding

class ConsumptionDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumptionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var kmPerLtr: String? = intent.getStringExtra("kmPerLtr")
        var totalAmount: String? = intent.getStringExtra("totalAmount")
        var millage: String? = intent.getStringExtra("millage")
        var pricePerLtr: String? = intent.getStringExtra("pricePerLtr")
        var numberOfLiter: String? = intent.getStringExtra("numberOfLiter")
        var gasType: String? = intent.getStringExtra("gasType")
        var gasStation: String? = intent.getStringExtra("gasStation")
        var branch: String? = intent.getStringExtra("branch")
        var date: String? = intent.getStringExtra("date")

        binding.tvKmPerLiterShow.text = kmPerLtr
        binding.tvTotalAmountShow.text = totalAmount
        binding.tvMillage.text = millage
        binding.tvPricePerLiter.text = pricePerLtr
        binding.tvNumberOfLiter.text = numberOfLiter
        binding.tvGasType.text = gasType
        binding.tvGasStation.text = gasStation
        binding.tvBranch.text = branch
        binding.tvDate.text = date


    }
}