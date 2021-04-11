package com.example.finch.screens.employees

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.finch.R
import com.example.finch.adapters.EmployeeAdapter
import com.example.finch.databinding.ActivityMainBinding

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