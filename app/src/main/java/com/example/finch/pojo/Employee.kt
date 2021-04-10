package com.example.finch.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Employee (
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

    @SerializedName("specialty")
    @Expose
    val specialty: List<Specialty>? = null
)