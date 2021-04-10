package com.example.finch.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeResponse (
    @SerializedName("response")
    @Expose
    val response: List<Employee>
)