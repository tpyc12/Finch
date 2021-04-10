package com.example.finch.screens.employees

import com.example.finch.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EmployeeListPresenter(val view: EmployeesListView) {
    private val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val disposable = ApiFactory.apiService.getEmployees()
            .map { it.response }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showData(it)
            }, {
                view.showError()
            })
        compositeDisposable.add(disposable)
    }

    fun disposeDisposable() {
        compositeDisposable.let {
            compositeDisposable.dispose()
        }
    }
}