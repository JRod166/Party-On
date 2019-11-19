package com.example.partyon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_song.view.*


//override fun ImageView.fromUrl(url:String) {
//    Picasso.get().load(url).into(this)
//}

class songsPoolAdapter (val songs: List<poolSong>?, val clickListener: (poolSong) -> Unit) : RecyclerView.Adapter<songsPoolAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = songs!![position]
        (holder as ViewHolder).bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(com.example.partyon.R.layout.item_song_pool, parent, false))
    }

    override fun getItemCount(): Int {
        return songs!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(song: poolSong, clickListener: (poolSong)->Unit) {
            itemView.ivAlbum.fromUrl(song.image.toString())
            itemView.tvNameSong.text=song.title.toString()
            itemView.tvNameArtist.text=song.artist.toString()
            itemView.tvNameAlbum.text=song.album.toString()
            itemView.tvDuration.text=song.frequency.toString()
            itemView.setOnClickListener{clickListener(song)}
        }
    }
}