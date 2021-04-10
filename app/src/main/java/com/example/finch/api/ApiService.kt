package com.example.finch.api

import com.example.finch.pojo.EmployeeResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("testTask.json")
    fun getEmployees(): Observable<EmployeeResponse>
}