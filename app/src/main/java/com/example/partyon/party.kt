package com.example.partyon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.toast

class party : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)

        val intent = intent
        //val id = intent.getStringExtra("id")
        val name=intent.getStringExtra("name")
        //toast(id)

        //var code= findViewById(R.id.codeIn) as TextView
        var nameTV = findViewById(R.id.nameIn) as TextView

        //toast(id.toString())
        //code.text = id
        nameTV.text = name

    }
}
