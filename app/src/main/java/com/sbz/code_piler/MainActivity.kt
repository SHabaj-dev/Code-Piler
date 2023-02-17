package com.sbz.code_piler

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sbz.code_piler.databinding.ActivityMainBinding
import com.sbz.code_piler.utils.CodeHighlighter


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
                val text = binding.tvEditor.text.toString()
                val ch = CodeHighlighter()
                when (position) {
                    0 -> {
                        binding.imageView.setImageResource(R.drawable.c_icn)
                        binding.tvEditor.setText(ch.highlightCCode(text))
                    }
                    1 -> {
                        binding.imageView.setImageResource(R.drawable.cpp_icn)
                        binding.tvEditor.setText(ch.highlightCPPCode(text))
                    }
                    2 -> {
                        binding.imageView.setImageResource(R.drawable.java_icn)
                        binding.tvEditor.setText(ch.highlightJavaCode(text))
                    }
                    3 -> {
                        binding.imageView.setImageResource(R.drawable.python_icn)
                        binding.tvEditor.setText(ch.highlightPythonCode(text))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /*Something to do here*/
            }
        }
    }





}