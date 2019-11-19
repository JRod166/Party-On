package com.example.partyon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_new_song.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class newSong : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var adapterSongs:songsAdapter
    lateinit var party_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_song)
        searchSong.setOnQueryTextListener(this)
        party_id = intent.extras!!.getString("id").toString()
    }
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
        val response = mAPIService2.addPool(party_id,songItem.id.toString(),songItem.album?.coverMedium.toString(),
            songItem.title.toString(),songItem.album?.title.toString(),
            songItem.artist?.name.toString(),songItem.duration.toString()).execute()
        val body= response.body()
        if(body!!.status.toString()=="400")
        {
            toast("Ha ocurrido un error")
        }
        finish()

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
