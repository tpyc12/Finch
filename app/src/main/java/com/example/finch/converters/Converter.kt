package com.example.finch.converters

import androidx.room.TypeConverter
import com.example.finch.pojo.Specialty
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun listSpecialtyToString(specialities: List<Specialty>): String? {
        return Gson().toJson(specialities)
    }

    @TypeConverter
    fun stringToListSpecialty (specialitiesAsString: String): List<Specialty> {
        val gson = Gson()
        val objects = gson.fromJson(specialitiesAsString, ArrayList::class.java)
        val specialities = mutableListOf<Specialty>()
        for (i in objects){
            specialities.add(gson.fromJson(i.toString(), Specialty::class.java ))
        }
        return specialities
    }
}