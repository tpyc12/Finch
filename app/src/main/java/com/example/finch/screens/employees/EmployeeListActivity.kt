package com.example.finch.screens.employees

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.finch.R
import com.example.finch.adapters.EmployeeAdapter
import com.example.finch.databinding.ActivityMainBinding
import com.example.finch.pojo.Specialty

class EmployeeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: EmployeeAdapter

    lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = EmployeeAdapter()
        binding.rvEmployees.adapter = adapter
        viewModel = ViewModelProviders.of(this)[EmployeeViewModel::class.java]
        viewModel.employees.observe(this, {
            adapter.employees = it
            if (it != null) {
                for (employee in it) {
                    val specialities: List<Specialty>? = employee.specialty
                    if (specialities != null) {
                        for (specialty in specialities) {
                            Log.i("Specialty", specialty.name.toString())
                        }
                    }
                }
            }
        })
        viewModel.errors.observe(this, {
            if (it != null) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                viewModel.clearErrors()
            }
        })
        viewModel.loadData()
    }
}