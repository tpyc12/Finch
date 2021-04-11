package com.example.finch.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finch.pojo.Employee
import io.reactivex.Completable

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(employees: List<Employee>): Completable

    @Query("DELETE FROM employees")
    fun deleteAllEmployees(): Completable
}