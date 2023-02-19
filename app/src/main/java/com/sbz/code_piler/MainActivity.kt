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
        test()

        //sending
        /*val client = OkHttpClient()

// Create a JSON object with the data to be posted
        val json = JSONObject()
        json.put("lang", "c++")
        json.put("input", "")
        json.put("code", "console.log")

// Create a request body with the JSON data
        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())

// Create a request object with the API endpoint URL and the request body
        val request = Request.Builder()
            .url("https://example.com/api")
            .post(requestBody)
            .build()

// Send the request asynchronously
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle the response here
            }
        })*/

        //Create a JSON object with the data to be posted
        /*val json = JSONObject()
        json.put("name", "prabhat")
        json.put("job", "leader")*/
        /*val editTextValue = editText.text.toString()*/


        /* val json = JSONObject()
         json.put("lang", "java")
         json.put("input", "")
         json.put("code", editTextValue)*/


        /*val dataModel=DataModel("java","",editTextValue)
        val json2=JSONObject(dataModel)
        val jsonString=json2.toString()*/
        /*
        String program = "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}";

        */
        // val program = "#include <iostream>\r\n\r\nint main() {\r\n    std::cout << \"Hello World!\";\r\n  return 0;\r\n}"
       //new

        val lang = "java"
        val code = """
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, World!");
            }
        }
    """.trimIndent()

        val data = JSONObject(mapOf("lang" to lang, "input" to "", "code" to code))


        val program = """
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, World!");
            }
        }
    """.trimIndent()
        val editTextValue = editText.setText(program).toString()
        val json = JSONObject()
        json.put("lang", "java")
        json.put("input", "")
        json.put("code", code)
        print(json.toString())
        val jsonString = "{\"code\":\"#include <iostream>\\r\\n\\r\\nint main() {\\r\\n    std::cout << \\\"Hello World!\\\";\\r\\n    return 0;\\r\\n}\",\"language\":\"cpp\",\"input\":\"\"}"


        val client = OkHttpClient()

        //https://reqres.in/api/users
        val url = "https://reqres.in/api/users"
        val myurl = "http://127.0.0.1:5000/compile"
        val urlnew="https://api.codex.jaagrav.in"
        // Create a request body with the JSON data
        btn.setOnClickListener {
            val requestBody =
                RequestBody.create("application/json".toMediaTypeOrNull(), jsonString)


            val request: Request = Request.Builder()
                .url(urlnew)
                .post(requestBody)
                .build()


            client.newCall(request).enqueue(object : Callback {
                /*fun onFailure(call: Call?, e: IOException) {
                    e.printStackTrace()
                }

                @Throws(IOException::class)
                fun onResponse(call: Call?, response: Response) {
                    if (response.isSuccessful) {
                        val myResponse = response.body!!.string()
                        runOnUiThread { mTextViewResult.setText(myResponse) }
                    }
                }*/

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


    fun new() {

        val lang = "java"
        val code = """
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, World!");
            }
        }
    """.trimIndent()

        val data = JSONObject(mapOf("lang" to lang, "input" to "", "code" to code))

        val apiUrl = "http://127.0.0.1:5000/compile"
        val url = URL(apiUrl)
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val outputStream = connection.outputStream
        outputStream.write(data.toString().toByteArray())
        outputStream.flush()
        outputStream.close()

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // handle successful response
           // Log.d("ashif",response.body!!.string())
        } else {
            // handle error response
        }
    }


    fun test() {
        val code = """
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, World!");
            }
        }
    """.trimIndent()

        val json = JSONObject(mapOf("program" to code))
        println(json.toString())
        Log.d("fuck", json.toString())
    }




}




