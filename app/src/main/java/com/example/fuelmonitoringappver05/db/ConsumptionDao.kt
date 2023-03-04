package com.example.fuelmonitoringappver05.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fuelmonitoringappver05.Consumptions

@Dao
interface ConsumptionDao {
    @Insert
    fun addConsumption(consumptions: Consumptions)

    @Query("SELECT * FROM Consumptions")
    fun getAllConsumptions():MutableList<Consumptions>

    @Query("UPDATE Consumptions SET date = :date WHERE id = :id")
    fun updateConsumptions(date:String, id:Int)

    @Query("DELETE FROM Consumptions WHERE id = :id")
    fun deleteConsumption(id:Int)


}