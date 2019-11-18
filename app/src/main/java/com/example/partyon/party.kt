package com.example.partyon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.toast

class party : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)

        val intent = intent
        //val id = intent.getStringExtra("id")
        val name=intent.getStringExtra("name")
        val latitude=intent.getStringExtra("latitude")
        val longitude=intent.getStringExtra("longitude")
        Toast.makeText(this, latitude+" "+longitude, Toast.LENGTH_LONG).show()
        //toast(id)
        var btnNewSong= findViewById(R.id.addSong) as Button
        //var code= findViewById(R.id.codeIn) as TextView
        var nameTV = findViewById(R.id.nameIn) as TextView

        btnNewSong.setOnClickListener {
            val addSongIntent = Intent(this,addSong::class.java)
            startActivity(addSongIntent)

        }

        //toast(id.toString())
        //code.text = id
        nameTV.text = name

    }
}
