package com.example.partyon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.partiesAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_party.view.*
import kotlinx.android.synthetic.main.item_song.view.*

fun ImageView.fromUrl(url:String){
    Picasso.get().load(url).into(this)
}

class songsAdapter (val songs: List<Datum>?, val clickListener: (Datum) -> Unit) : RecyclerView.Adapter<songsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = songs!![position]
        (holder as ViewHolder).bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(com.example.partyon.R.layout.item_song, parent, false))
    }

    override fun getItemCount(): Int {
        return songs!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(song: Datum, clickListener: (Datum)->Unit) {
            itemView.ivAlbum.fromUrl(song.album!!.coverMedium!!.toString())
            itemView.tvNameSong.text=song.title.toString()
            itemView.tvNameArtist.text=song.artist!!.name.toString()
            itemView.tvNameAlbum.text=song.album!!.title.toString()
            itemView.tvDuration.text=song.duration.toString()
            itemView.setOnClickListener{clickListener(song)}
        }
    }
}