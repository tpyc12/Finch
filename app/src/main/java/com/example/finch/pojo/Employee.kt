package com.example.finch.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "employees")
data class Employee (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("f_name")
    @Expose
    val fName: String? = null,

    @SerializedName("l_name")
    @Expose
    val lName: String? = null,

    @SerializedName("birthday")
    @Expose
    val birthday: String? = null,

    @SerializedName("avatr_url")
    @Expose
    val avatrUrl: String? = null,

//    @SerializedName("specialty")
//    @Expose
//    val specialty: List<Specialty>? = null
)