package com.example.finch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finch.adapters.EmployeeAdapter
import com.example.finch.api.ApiFactory
import com.example.finch.databinding.ActivityMainBinding
import com.example.finch.pojo.Employee
import com.example.finch.pojo.EmployeeResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = EmployeeAdapter()
        binding.rvEmployees.adapter = adapter

        val disposable = ApiFactory.apiService.getEmployees()
            .map { it.response }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.employees = it
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}