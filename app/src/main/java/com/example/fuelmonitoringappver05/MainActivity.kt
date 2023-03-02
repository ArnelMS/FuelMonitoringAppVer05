package com.example.fuelmonitoringappver05

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fuelmonitoringappver05.databinding.ActivityMainBinding
import com.example.fuelmonitoringappver05.databinding.AddDialogBoxBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var consumptionList = mutableListOf<Consumptions>(
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
            Consumptions("13", "3000", "500", "65", "32", "XCS", "Petron", "Marikina","02/23/2023"),
        )
        //pass data source to adapter
        val adapter = ConsumptionAdapter(consumptionList)
            adapter.onItemClick = {
                val intent = Intent(this, ConsumptionDetailActivity::class.java)

                intent.putExtra("kmPerLtr", it.kmPerLtr)
                intent.putExtra("totalAmount", it.totalAmount)
                intent.putExtra("millage", it.millage)
                intent.putExtra("pricePerLtr", it.pricePerLtr)
                intent.putExtra("numberOfLiter", it.numberOfLiter)
                intent.putExtra("gasType", it.gasType)
                intent.putExtra("gasStation", it.gasStation)
                intent.putExtra("branch", it.branch)
                intent.putExtra("date", it.date)
                startActivity(intent)


//                Toast.makeText(applicationContext,it.gasStation,Toast.LENGTH_SHORT).show()
            }
//***************************************************************************************************************************
        binding.floatingBtnMenuFAB.setOnClickListener(){

            // on below line we are creating variable for all
            // floating action buttons and a boolean variable.
            lateinit var menuFab: FloatingActionButton
            lateinit var homeFAB: FloatingActionButton
            lateinit var addFuelFAB: FloatingActionButton
            var fabVisible = false


                // initializing variables of floating
                // action button on below line.
                menuFab = findViewById(R.id.floatingBtnMenuFAB)
                homeFAB = findViewById(R.id.floatingBtnHomeFAB)
                addFuelFAB = findViewById(R.id.floatingBtnAddFuelFAB)


                // on below line we are initializing our
                // fab visibility boolean variable
                fabVisible = false

                // on below line we are adding on click listener
                // for our add floating action button
                menuFab.setOnClickListener {
                    // on below line we are checking
                    // fab visible variable.
                    if (!fabVisible) {

                        // if its false we are displaying home fab
                        // and settings fab by changing their
                        // visibility to visible.
                        homeFAB.show()
                        addFuelFAB.show()

                        // on below line we are setting
                        // their visibility to visible.
                        homeFAB.visibility = View.VISIBLE
                        addFuelFAB.visibility = View.VISIBLE

                        // on below line we are checking image icon of add fab
                        menuFab.setImageDrawable(resources.getDrawable(R.drawable.fuel_icon))

                        // on below line we are changing
                        // fab visible to true
                        fabVisible = true
                    } else {

                        // if the condition is true then we
                        // are hiding home and settings fab
                        homeFAB.hide()
                        addFuelFAB.hide()

                        // on below line we are changing the
                        // visibility of home and settings fab
                        homeFAB.visibility = View.GONE
                        addFuelFAB.visibility = View.GONE

                        // on below line we are changing image source for add fab
                        menuFab.setImageDrawable(resources.getDrawable(R.drawable.fuel_icon))

                        // on below line we are changing
                        // fab visible to false.
                        fabVisible = false
                    }
                }

                // on below line we are adding
                // click listener for our home fab
                homeFAB.setOnClickListener {
                    // on below line we are displaying a toast message.
                    Toast.makeText(this@MainActivity, "Home clicked..", Toast.LENGTH_LONG).show()
                }

                // on below line we are adding on
                // click listener for settings fab
                addFuelFAB.setOnClickListener {
                    showAddDialog()
                    // on below line we are displaying a toast message
                    Toast.makeText(this@MainActivity, "Settings clicked..", Toast.LENGTH_LONG).show()
                }
            }

//***************************************************************************************************************************

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun showAddDialog(){
        val dialog = Dialog(this)
        val binding: AddDialogBoxBinding = AddDialogBoxBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()
    }
}