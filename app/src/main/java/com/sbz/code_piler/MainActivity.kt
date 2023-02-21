package com.sbz.code_piler


import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.sbz.code_piler.DataModel.ApiResponse
import com.sbz.code_piler.databinding.ActivityMainBinding
import com.sbz.code_piler.utils.CodeHighlighter
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException


//import okio.ByteString.Companion.utf8


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btn: AppCompatButton
    private var selectedLanguage = "c"
    private lateinit var languageIndex: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        // editText = findViewById(R.id.tv_editor)
        btn = findViewById(R.id.btn_run)
        setSpinnerItem()
        setImageResource()
        //selectLanguageIndex()

        btn.setOnClickListener {
            getApiResponse()
//            Toast.makeText(this, selectedLanguage, Toast.LENGTH_LONG).show()
        }

    }

    private fun getApiResponse() {
        val textInputEditText = findViewById<TextInputEditText>(R.id.tv_editor)
        val inputCode = textInputEditText.text.toString()
        val json = JSONObject()
        json.put("code", inputCode)

        json.put("language", selectedLanguage)
        json.put("input", "")
        val client = OkHttpClient()
        Log.d("pt",inputCode+selectedLanguage)

        val requestBody =
            RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())

        val request: Request = Request.Builder()
            .url(BASE_URL)
            .post(requestBody)
            .build()


        client.newCall(request).enqueue(object : Callback {


            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val apiResponse =
                        gson.fromJson(response.body?.string(), ApiResponse::class.java)
                    Log.d("apiResponse", apiResponse.toString())
                    val etOutput = findViewById<TextView>(R.id.et_Output)
                    Log.d("et_Output", apiResponse.output)
                    runOnUiThread {
                        // Stuff that updates the UI
                        val mOutput = apiResponse.output
                        Log.d("mOutput",mOutput)
                        etOutput.text = mOutput

                    }
                }
            }
        })

    }

    private fun selectLanguageIndex() {

        binding.mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
              languageIndex = parent?.getItemIdAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        setLanguage(languageIndex)
    }

    private fun setLanguage(languageIndex: String) {
        when(languageIndex){
            "0" -> selectedLanguage = "c"
            "1" -> selectedLanguage = "cpp"
            "2" -> selectedLanguage = "java"
            "3" -> selectedLanguage = "py"
        }
    }

    private fun setSpinnerItem() {
        val itemList = listOf("C", "C++", "Java", "Python")
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

                       // binding.tvEditor.setText(ch.highlightCCode(text))
                        selectedLanguage="c"
                        binding.tvEditor.setText(ch.highlightCode(text, C_KEYWORDS))
                    }
                    1 -> {
                        binding.imageView.setImageResource(R.drawable.cpp_icn)
                        binding.tvEditor.setText(ch.highlightCode(text, CPP_KEYWORDS))
                        selectedLanguage="cpp"
                    }
                    2 -> {
                        binding.imageView.setImageResource(R.drawable.java_icn)
                        binding.tvEditor.setText(ch.highlightCode(text, JAVA_KEYWORDS))
                        selectedLanguage="java"
                    }
                    3 -> {
                        binding.imageView.setImageResource(R.drawable.python_icn)
                        binding.tvEditor.setText(ch.highlightCode(text, PYTHON_KEYWORDS))
                        selectedLanguage="py"
                    }
                }

                languageIndex = parent?.getItemIdAtPosition(position).toString()
                setLanguage(languageIndex)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /*Something to do here*/
            }
        }
    }


}




