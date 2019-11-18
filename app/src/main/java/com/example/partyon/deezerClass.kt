package com.example.partyon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Album {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("cover")
    @Expose
    var cover: String? = null
    @SerializedName("cover_small")
    @Expose
    var coverSmall: String? = null
    @SerializedName("cover_medium")
    @Expose
    var coverMedium: String? = null
    @SerializedName("cover_big")
    @Expose
    var coverBig: String? = null
    @SerializedName("cover_xl")
    @Expose
    var coverXl: String? = null
    @SerializedName("tracklist")
    @Expose
    var tracklist: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}

class Artist {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("picture")
    @Expose
    var picture: String? = null
    @SerializedName("picture_small")
    @Expose
    var pictureSmall: String? = null
    @SerializedName("picture_medium")
    @Expose
    var pictureMedium: String? = null
    @SerializedName("picture_big")
    @Expose
    var pictureBig: String? = null
    @SerializedName("picture_xl")
    @Expose
    var pictureXl: String? = null
    @SerializedName("tracklist")
    @Expose
    var tracklist: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}

class Datum {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("readable")
    @Expose
    var readable: Boolean? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("title_short")
    @Expose
    var titleShort: String? = null
    @SerializedName("title_version")
    @Expose
    var titleVersion: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("duration")
    @Expose
    var duration: String? = null
    @SerializedName("rank")
    @Expose
    var rank: String? = null
    @SerializedName("explicit_lyrics")
    @Expose
    var explicitLyrics: Boolean? = null
    @SerializedName("explicit_content_lyrics")
    @Expose
    var explicitContentLyrics: Int? = null
    @SerializedName("explicit_content_cover")
    @Expose
    var explicitContentCover: Int? = null
    @SerializedName("preview")
    @Expose
    var preview: String? = null
    @SerializedName("artist")
    @Expose
    var artist: Artist? = null
    @SerializedName("album")
    @Expose
    var album: Album? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}

class Songs {

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null

}