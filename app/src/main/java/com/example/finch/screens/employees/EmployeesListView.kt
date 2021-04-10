package com.example.finch.screens.employees

import com.example.finch.pojo.Employee

interface EmployeesListView {
    fun showData(employees: List<Employee>)
    fun showError()
}