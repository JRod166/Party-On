package com.example.partyon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_party.*
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.fixedRateTimer

class party : AppCompatActivity() {

    lateinit var adapterSongs:songsPoolAdapter
    lateinit var id_party: String

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fast-sands-11156.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)

        val intent = intent
        val name=intent.getStringExtra("name")
        id_party = intent.getStringExtra("id").toString()
        val latitude=intent.getStringExtra("latitude")
        val longitude=intent.getStringExtra("longitude")
        //Toast.makeText(this, latitude+" "+longitude, Toast.LENGTH_LONG).show()
        //toast(id)
        var btnNewSong= findViewById(R.id.addSong) as Button
        //var code= findViewById(R.id.codeIn) as TextView
        var nameTV = findViewById(R.id.nameIn) as TextView

        btnNewSong.setOnClickListener {
            val addSongIntent = Intent(this,newSong::class.java)
            addSongIntent.putExtra("id",id_party)
            toast("ok")
            startActivity(addSongIntent)

        }

        fixedRateTimer("timer",false,0,5000){
            this@party.runOnUiThread {
                var mAPIService = getRetrofit().create(APIService::class.java)
                val response = mAPIService.getTopTen("/api/v1/get_top_ten/$id_party").execute()
                val body=response.body()
                addAdapter(body?.content)


            }
        }

        //toast(id.toString())
        //code.text = id
        nameTV.text = name

    }

    private fun addAdapter(songs: List<poolSong>?){
        adapterSongs= songsPoolAdapter(songs,{ songItem : poolSong-> songItemClicked(songItem)})
        rvSongPool.layoutManager = LinearLayoutManager(this)
        rvSongPool.adapter = adapterSongs
    }

    private fun songItemClicked(songItem:poolSong){
        var mAPIService2 = getRetrofit().create(APIService::class.java)
        mAPIService2.vote(songItem.id.toString(),id_party).execute()
        var mAPIService = getRetrofit().create(APIService::class.java)
        val response = mAPIService.getTopTen("/api/v1/get_top_ten/$id_party").execute()
        val body=response.body()
        addAdapter(body?.content)
        toast(songItem.frequency.toString())
    }
}
