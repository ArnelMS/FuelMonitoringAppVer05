package com.example.fuelmonitoringappver05

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fuelmonitoringappver05.databinding.ActivityMainBinding
import com.example.fuelmonitoringappver05.databinding.AddDialogBoxBinding
import com.example.fuelmonitoringappver05.databinding.InfoDialogBoxBinding
import com.example.fuelmonitoringappver05.databinding.UpdateDialogBoxBinding
import com.example.fuelmonitoringappver05.db.ConsumptionDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ConsumptionAdapter
    lateinit var fuelConsumptionDB: ConsumptionDatabase
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String
    lateinit var textView: TextView
    lateinit var calendar: Calendar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fuelConsumptionDB = ConsumptionDatabase.invoke(this)
        view()

        binding.btnInfo.setOnClickListener() {
            val infoDialogue = Dialog(this)
            val binding: InfoDialogBoxBinding = InfoDialogBoxBinding.inflate(layoutInflater)
            infoDialogue.setContentView(binding.root)
            infoDialogue.show()

            Toast.makeText(applicationContext, "SHOWING APP INFO", Toast.LENGTH_SHORT).show()

        }
        binding.imgBtnMenu.setOnClickListener() {

            Toast.makeText(applicationContext, "SHOWING MENU", Toast.LENGTH_SHORT).show()

        }
//*********************  DATE AND TIME  ******************************************************************************************************
        textView = binding.tvCurrentDate
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("EEE | MMM dd, yyyy")
        date = simpleDateFormat.format(calendar.time)
        textView.text = date

//*********************  FLOATING BUTTONS  ******************************************************************************************************

        binding.floatingBtnMenuFAB.setOnClickListener() {

            // on below line we are creating variable for all // floating action buttons and a boolean variable.
            // initializing variables of floating // action button on below line.
            var menuFab: FloatingActionButton = findViewById(R.id.floatingBtnMenuFAB)
            var homeFAB: FloatingActionButton = findViewById(R.id.floatingBtnHomeFAB)
            var addFuelFAB: FloatingActionButton = findViewById(R.id.floatingBtnAddFuelFAB)
            var fabVisible = false

            // initializing fab visibility boolean variable
            fabVisible = false

            // adding on click listener
            // add floating action button
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

            homeFAB.setOnClickListener {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@MainActivity, "Home clicked..", Toast.LENGTH_LONG).show()
            }

            addFuelFAB.setOnClickListener {
                showAddDialog()

                Toast.makeText(this@MainActivity, "Add Gas Up Information!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    //*********************  ADD DIALOG BOX  ******************************************************************************************************
    fun showAddDialog() {
        val dialog = Dialog(this)
        val binding: AddDialogBoxBinding = AddDialogBoxBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

//*********************  SPINNER  ******************************************************************************************************
        val spinnerData = arrayListOf<String>(
            "Select Gas Station",
            "Petron",
            "Shell",
            "Caltex",
            "Petro_Gazz",
            "Phoenix",
            "Seaoil",
            "Flying V",
            "Unioil",
            "Clean Fuel",
            "FlexFuel",
            "PTT",
            "Total",
            "Others..."
        )

        val spinnerAdapter = ArrayAdapter(applicationContext, R.layout.textview, spinnerData)

        binding.spinnerGasStation.adapter = spinnerAdapter

//        var gasCompany: Int =
//            resources.getIdentifier(binding.spinnerGasStation.toString(), "drawable", packageName)


//*********************  ROOM DATABASE FUNCTIONS  ******************************************************************************************************

        binding.imgBtnSave.setOnClickListener() {
            var kmPerLtr: String = binding.etKmPerLiterShow.text.toString()
            var totalAmount: String = binding.etTotalAmountShow.text.toString()
            var millage: String = binding.etMillage.text.toString()
            var pricePerLtr: String = binding.etPricePerLiter.text.toString()
            var numberOfLiter: String = binding.etNumberOfLiter.text.toString()
            var gasType: String = binding.etGasType.text.toString()
            var gasStation: String = binding.spinnerGasStation.selectedItem.toString()
            var branch: String = binding.etBranch.text.toString()
            var date: String = binding.etNewDate.text.toString()

            val consumption = Consumptions(
                kmPerLtr,
                totalAmount,
                millage,
                pricePerLtr,
                numberOfLiter,
                gasType,
                gasStation,
                branch,
                date
            )
            AlertDialog.Builder(this)
                .setMessage("Save details?")
                .setPositiveButton("YES") { dialog, item ->
                    save(consumption)
                    adapter.consumptions.add(consumption)
                    adapter.notifyDataSetChanged()

                    adapter.consumptions.add(
                        Consumptions(
                            kmPerLtr,
                            totalAmount,
                            millage,
                            pricePerLtr,
                            numberOfLiter,
                            gasType,
                            gasStation,
                            branch,
                            date
                        )
                    )
                    Toast.makeText(applicationContext, "Detail Saved!", Toast.LENGTH_SHORT).show()

                }.setNegativeButton("Cancel") { dialog, item ->
                    dialog.dismiss()
                }.show()
        }
        binding.imgBtnCancel.setOnClickListener() {
            dialog.dismiss()
        }
    }

    private fun save(consumption: Consumptions) {
        GlobalScope.launch(Dispatchers.IO) {
            fuelConsumptionDB.getConsumptions().addConsumption(consumption)
        }
    }

    private fun view() {
        lateinit var consumption: MutableList<Consumptions>
        val intent = Intent(this, ConsumptionDetailActivity::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            consumption = fuelConsumptionDB.getConsumptions().getAllConsumptions()

            withContext(Dispatchers.Main) {
                adapter = ConsumptionAdapter(consumption)
                adapter.onItemClick = {


                    intent.putExtra("kmPerLtr", it.kmPerLtr)
                    intent.putExtra("kmPerLtr", it.kmPerLtr)
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

//                    Toast.makeText(applicationContext,it.gasStation,Toast.LENGTH_SHORT).show()
                }


                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)


//                Toast.makeText(applicationContext, consumption.toString(),Toast.LENGTH_SHORT).show()

                adapter.onDeleteButtonClick = { item: Consumptions, position: Int ->
                    delete(item)
                    adapter.consumptions.removeAt(position)
                    adapter.notifyDataSetChanged()

                    Toast.makeText(applicationContext, "ITEM DELETED", Toast.LENGTH_SHORT).show()
                }
                adapter.onUpdateButtonClick = { item: Consumptions, position: Int ->

                    showUpdateDialog(item.id)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "ITEM UPDATED", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun delete(consumption: Consumptions) {
        GlobalScope.launch(Dispatchers.IO) {
            fuelConsumptionDB.getConsumptions().deleteConsumption(consumption.id)
            view()
        }
    }

//*********************  DIALOG BOX    ******************************************************************************************************

    private fun showUpdateDialog(id: Int) {
        val dialog = Dialog(this)
        val binding: UpdateDialogBoxBinding = UpdateDialogBoxBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.imgBtnSave.setOnClickListener() {
            var newKmPerLtr: String = binding.etKmPerLiterShow.text.toString()
            var newTotalAmount: String = binding.etTotalAmountShow.text.toString()
            var newMillage: String = binding.etMillage.text.toString()
            var newPricePerLtr: String = binding.etPricePerLiter.text.toString()
            var newNumberOfLiter: String = binding.etNumberOfLiter.text.toString()
            var newGasType: String = binding.etGasType.text.toString()
            var newGasStation: String = binding.spinnerGasStation.toString()
            var newBranch: String = binding.etBranch.text.toString()
            var newDate: String = binding.etNewDate.text.toString()


            AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Update?")
                .setPositiveButton("YES") { dialog, item ->
                    GlobalScope.launch(Dispatchers.IO) {
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newKmPerLtr, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newTotalAmount, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newMillage, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newPricePerLtr, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newNumberOfLiter, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newGasType, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newGasStation, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newBranch, id)
                        fuelConsumptionDB.getConsumptions().updateConsumptions(newDate, id)
                        view()
                    }

                }.setNegativeButton("Cancel") { dialog, item ->
                    dialog.dismiss()
                }.show()
        }
        binding.imgBtnCancelUpdate.setOnClickListener() {
            dialog.dismiss()
        }
    }

}