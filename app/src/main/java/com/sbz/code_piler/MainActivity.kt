package com.sbz.code_piler


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import com.sbz.code_piler.databinding.ActivityMainBinding
import com.sbz.code_piler.utils.CodeHighlighter
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mTextViewResult: TextView
    private lateinit var editText: TextInputEditText
    private lateinit var btn: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        // mTextViewResult=findViewById(R.id.tv_output)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        editText = findViewById(R.id.tv_editor)
        btn = findViewById(R.id.btn_run)

        setSpinnerItem()
        setImageResource()


        //val data = JSONObject(mapOf("lang" to lang, "input" to "", "code" to code))




       // print(json.toString())
        //val jsonString = "{\"code\":\"#include <iostream>\\r\\n\\r\\nint main() {\\r\\n    std::cout << \\\"Hello World!\\\";\\r\\n    return 0;\\r\\n}\",\"language\":\"cpp\",\"input\":\"\"}"
       /* val jsonString = "{\"code\":\" #include <stdio.h>\\n\\n int main()\\n{\\n\\n\\n\\t printf(\\\"Hello World\\\");\\n\\n\\t return 0;\\n}\\n \",\"language\":\"c\",\"input\":\"\"}"*/
        //public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}
        // val jsonString = "{\"code\":\" public class HelloWorld {\\n    public static void main(String[] args) {\\n        System.out.println(\\\"Hello, World!\\\");\\n    }\\n} \",\"language\":\"java\",\"input\":\"\"}"



        val program = """
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, World!");
            }
        }
    """.trimIndent()
        val editTextValue = editText.setText(program).toString()
        val json = JSONObject()
        json.put("code", program)
        json.put("language", "java")
        json.put("input", "")

        val client = OkHttpClient()

        //https://reqres.in/api/users
       /* val url = "https://reqres.in/api/users"
        val myurl = "http://127.0.0.1:5000/compile"*/
        val urlnew="https://api.codex.jaagrav.in"
        // Create a request body with the JSON data
        btn.setOnClickListener {
            /*val requestBody =
                RequestBody.create("application/json".toMediaTypeOrNull(), jsonString)*/
            val requestBody =
                RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())


            val request: Request = Request.Builder()
                .url(urlnew)
                .post(requestBody)
                .build()


            client.newCall(request).enqueue(object : Callback {


                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val myResponse = response.body!!.toString()
                        //Log.d("PrabhatTiwari","myResponse")
                        Log.d("onResponse", response.body!!.string())
                        //runOnUiThread(mTextViewResult.setText(myResponse))
                    }
                }
            })

        }


    }

    private fun setSpinnerItem() {
        val itemList = listOf<String>("C", "C++", "Java", "Python")
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item, itemList
        )

        adapter.setDropDownViewResource(R.layout.spinner_item)
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
                        binding.tvEditor.setText(ch.highlightCode(text, C_KEYWORDS))
                    }
                    1 -> {
                        binding.imageView.setImageResource(R.drawable.cpp_icn)
                        binding.tvEditor.setText(ch.highlightCode(text, CPP_KEYWORDS))
                    }
                    2 -> {
                        binding.imageView.setImageResource(R.drawable.java_icn)
                        binding.tvEditor.setText(ch.highlightCode(text, JAVA_KEYWORDS))
                    }
                    3 -> {
                        binding.imageView.setImageResource(R.drawable.python_icn)
                        binding.tvEditor.setText(ch.highlightCode(text, PYTHON_KEYWORDS))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /*Something to do here*/
            }
        }
    }






}




