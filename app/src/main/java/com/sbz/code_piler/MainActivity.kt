package com.sbz.code_piler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.sbz.code_piler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        setSpinnerItem()
        setImageResource()


    }

    private fun setSpinnerItem() {
        val itemList = resources.getStringArray(R.array.options_array)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, itemList
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mySpinner.adapter = adapter


    }

    private fun setImageResource() {
        binding.mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> binding.imageView.setImageResource(R.drawable.c_icn)
                    1 -> binding.imageView.setImageResource(R.drawable.cpp_icn)
                    2 -> binding.imageView.setImageResource(R.drawable.java_icn)
                    3 -> binding.imageView.setImageResource(R.drawable.python_icn)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}