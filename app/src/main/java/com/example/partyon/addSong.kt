package com.example.partyon

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.partiesAdapter

import kotlinx.android.synthetic.main.activity_add_song.*
import kotlinx.android.synthetic.main.activity_parties.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class addSong : AppCompatActivity(),SearchView.OnQueryTextListener {
    lateinit var adapterSongs:songsAdapter

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getRetrofit2(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fast-sands-11156.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun songItemClicked(songItem : Datum) {
        var mAPIService2 = getRetrofit2().create(APIService::class.java)
        toast("ToDo")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)
        searchSong.setOnQueryTextListener(this)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchByName(query!!.toLowerCase())
        return true
    }

    private fun searchByName(query: String) {
        toast("buscando")
        var mAPIService=getRetrofit().create(APIService::class.java)
        val response=mAPIService.searchSongs("search?q=$query").execute()
        val body=response.body()
        if(body!!.total != 0) {
            addAdapter(body!!.data)
        }else{
            showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }

    private fun addAdapter(songs: List<Datum>?){
        adapterSongs= songsAdapter(songs,{songItem : Datum -> songItemClicked(songItem)})
        rvSongs.layoutManager = LinearLayoutManager(this)
        rvSongs.adapter = adapterSongs
    }

}
