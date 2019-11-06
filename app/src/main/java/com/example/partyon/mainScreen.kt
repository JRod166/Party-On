package com.example.partyon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class mainScreen : AppCompatActivity() {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.43.23:80/api/v1/join_party/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val code = findViewById(R.id.partyCode) as EditText
        val name = findViewById(R.id.partyName) as EditText
        val join = findViewById(R.id.joinParty) as Button
        val create = findViewById(R.id.createParty) as Button

        val intent = intent
        val id = intent.getStringExtra("id")

        join.setOnClickListener{
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response=mAPIService.join(code.text.toString()).execute()
            val body=response.body()
            if(body!!.getStatus() == "200") {
                toast(body.getName().toString())
                val intentParty = Intent(this,party::class.java)
                intentParty.putExtra("id",body.getId())
                intentParty.putExtra("name",body.getName())
                startActivity(intentParty)
            }
        }
        create.setOnClickListener{
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response=mAPIService.create(name.text.toString(),id.toString()).execute()
            val body=response.body()
            if(body!!.getStatus() == "200") {
                toast(body.getId().toString())
                val intentParty = Intent(this,party::class.java)
                intentParty.putExtra("id",body.getId())
                intentParty.putExtra("name",body.getName())
                startActivity(intentParty)
            }
        }
    }
}
