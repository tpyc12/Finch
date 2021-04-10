package com.example.finch.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Specialty (
    @SerializedName("specialty_id")
    @Expose
    val specialtyId: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null
)