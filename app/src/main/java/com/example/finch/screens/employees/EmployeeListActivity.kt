package com.example.finch.screens.employees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finch.R
import com.example.finch.adapters.EmployeeAdapter
import com.example.finch.api.ApiFactory
import com.example.finch.databinding.ActivityMainBinding
import com.example.finch.pojo.Employee
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EmployeeListActivity : AppCompatActivity(), EmployeesListView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: EmployeeListPresenter

    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = EmployeeListPresenter(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = EmployeeAdapter()
        binding.rvEmployees.adapter = adapter
        presenter.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeDisposable()
    }

    override fun showData(employees: List<Employee>) {
        adapter.employees = employees
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}