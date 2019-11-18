package com.example.partyon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class register : AppCompatActivity() {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fast-sands-11156.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_register)

        val name = findViewById(R.id.regName) as EditText
        val lastName = findViewById(R.id.regLastName) as EditText
        val mail = findViewById(R.id.regMail) as EditText
        val password =findViewById(R.id.regPassword) as EditText
        val registerBtn = findViewById(R.id.buttonReg) as Button
        registerBtn.setOnClickListener {
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response=mAPIService.register(name.text.toString(),lastName.text.toString(),mail.text.toString(),password.text.toString()).execute()
            if(response.body()!!.getStatus() == "406")
            {
                toast("Este email ya ha sido registrado")
            }
            else
            {
                toast("Ususario registrado")
                finish()
            }
        }
    }
}
