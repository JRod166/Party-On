package com.example.partyon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.partiesAdapter
import kotlinx.android.synthetic.main.activity_parties.*
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class parties : AppCompatActivity() {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fast-sands-11156.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun partItemClicked(partItem : partyClass) {
        var mAPIService = getRetrofit().create(APIService::class.java)
        val response=mAPIService.join(partItem.getId().toString()).execute()
        val body=response.body()
        if(body!!.getStatus() == "200") {
            toast(body.getName().toString())
            val intentParty = Intent(this,party::class.java)
            intentParty.putExtra("id",body.getId())
            intentParty.putExtra("name",body.getName())
            startActivity(intentParty)
        }
    }

    lateinit var adapterParties:partiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        //rvParties.setHasFixedSize(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parties)
        val latitude = intent.getStringExtra("latitude")
        val longitude = intent.getStringExtra("longitude")

        var mAPIService = getRetrofit().create(APIService::class.java)
        val response = mAPIService.closeParties(latitude,longitude).execute()
        val body = response.body()
        if(body!!.getStatus() == "200")
        {
            adapterParties= partiesAdapter(body.getContent(),{partItem : partyClass -> partItemClicked(partItem)})
            rvParties.layoutManager = LinearLayoutManager(this)
            rvParties.adapter = adapterParties
        }



    }
}
