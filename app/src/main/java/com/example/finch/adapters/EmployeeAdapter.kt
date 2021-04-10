package com.example.finch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finch.R
import com.example.finch.databinding.EmployeeItemBinding
import com.example.finch.pojo.Employee

class EmployeeAdapter: RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var employees: List<Employee> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employer = employees[position]
        with(holder){
            tvName.text = employer.fName
            tvLastName.text = employer.lName
        }
    }

    override fun getItemCount() = employees.size


    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding: EmployeeItemBinding = EmployeeItemBinding.bind(itemView)

        val tvName = binding.tvName
        val tvLastName = binding.tvLastName
    }
}