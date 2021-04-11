package com.example.finch.screens.employees

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.finch.api.ApiFactory
import com.example.finch.data.AppDatabase
import com.example.finch.pojo.Employee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val employees = db.employeeDao().getAllEmployees()
    val errors: MutableLiveData<Throwable> = MutableLiveData()

    fun clearErrors(){
        errors.value = null
    }

    @SuppressLint("CheckResult")
    private fun insertEmployees(employees: List<Employee>) {
        db.employeeDao().insertEmployees(employees)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("test", "Excelent")
            }, {
                Log.d("test", it.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    private fun deleteAllEmployees() {
        db.employeeDao().deleteAllEmployees()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("test", "Excelent")
            }, {
                Log.d("test", it.message.toString())
            })
    }

    fun loadData() {
        val disposable = ApiFactory.apiService.getEmployees()
            .map { it.response }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deleteAllEmployees()
                insertEmployees(it)
            }, {
                errors.value = it
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}