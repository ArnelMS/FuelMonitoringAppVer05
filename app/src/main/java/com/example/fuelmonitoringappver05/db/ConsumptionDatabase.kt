package com.example.fuelmonitoringappver05.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fuelmonitoringappver05.Consumptions

@Database(
    entities = [Consumptions::class],
    version = 1
)

abstract class ConsumptionDatabase : RoomDatabase(){
    abstract fun getConsumptions():ConsumptionDao

    companion object {
        @Volatile
        private var instance: ConsumptionDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ConsumptionDatabase::class.java,
            "fuelConsumption"
        ).build()
    }
}