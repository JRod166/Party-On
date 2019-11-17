package com.example.partyon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.toast
import android.os.StrictMode

class MainActivity : AppCompatActivity() {

    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.43.31:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)
        val email = findViewById(R.id.email) as EditText
        val password = findViewById(R.id.password) as EditText
        val submitBtn = findViewById(R.id.entrar) as Button
        val registerBtn = findViewById(R.id.registrar) as Button

        submitBtn.setOnClickListener {
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response=mAPIService.login(email.text.toString(), password.text.toString()).execute()
            val body= response.body()
            if(body!!.getStatus() == "401")
            {
                toast("Datos incorrectos, intente nuevamente")
            }
            else
            {
                val intentMain = Intent(this@MainActivity,mainScreen::class.java)
                intentMain.putExtra("id",body.getEmail())
                startActivity(intentMain)
            }
        }

        registerBtn.setOnClickListener{
            toast("Ahora vamos a registrarnos")
            val intentRegister = Intent(this, register::class.java)
            startActivity(intentRegister)
        }
    }

}
