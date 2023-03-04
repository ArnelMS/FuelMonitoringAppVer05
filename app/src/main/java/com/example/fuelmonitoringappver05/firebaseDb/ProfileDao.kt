package com.example.fuelmonitoringappver05.firebaseDb

import com.example.fuelmonitoringappver05.Profile.Profile
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileDao() {

    var dbReference : DatabaseReference = Firebase.database.reference

    fun add(profile: Profile){
        dbReference.push().setValue(profile)
    }

    fun get(): Query{
        return dbReference.orderByKey()
    }

    fun remove(key: String){
        dbReference.child(key).removeValue()
    }

    fun update(key:String, map:Map<String,String>){
        dbReference.child(key).updateChildren(map)
    }

}