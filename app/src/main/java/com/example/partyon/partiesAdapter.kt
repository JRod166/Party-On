package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.partyon.R
import com.example.partyon.partiesClass
import com.example.partyon.partyClass
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.item_party.view.*



fun ImageView.fromUrl(url:String){
    Picasso.get().load(url).into(this)
}

class partiesAdapter (val parties: List<partyClass>?, val clickListener: (partyClass) -> Unit) : RecyclerView.Adapter<partiesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = parties!![position]
        (holder as ViewHolder).bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(com.example.partyon.R.layout.item_party, parent, false))
    }

    override fun getItemCount(): Int {
        return parties!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(party: partyClass, clickListener: (partyClass)->Unit) {
            itemView.tvName.text = party.getName()
            itemView.setOnClickListener{clickListener(party)}
        }
    }
}