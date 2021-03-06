package com.example.partyon

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

public class statusClass {
    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("password")
    @Expose
    private var password: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status= status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(status: String) {
        this.message= status
    }
    override fun toString(): String {
        return "{"+
                "email='" + email + '\'' +
                ", password='"+password+ '\'' +
                ", status='"+status+ '\'' +
                ", message='"+message+ '\'' +
                '}'

    }
}

public class registerClass {
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("lastName")
    @Expose
    private var lastName: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("password")
    @Expose
    private var password: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName= lastName
    }
    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status= status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(status: String) {
        this.message= status
    }
    override fun toString(): String {
        return "{"+
                "email='" + email + '\'' +
                ", password='"+password+ '\'' +
                ", status='"+status+ '\'' +
                ", message='"+message+ '\'' +
                '}'

    }
}



public class partyClass{
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("host_user_id")
    @Expose
    private var host_user_id: String? = null
    @SerializedName("latitude")
    @Expose
    private var latitude:String? = null
    @SerializedName("longitude")
    @Expose
    private var longitude:String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getHost_user_id():String? {
        return  host_user_id
    }
    fun setHost_user_id(host_user_id:String){
        this.host_user_id=host_user_id
    }
    fun getLatitude():String? {
        return latitude
    }
    fun setLatitude(latitude:String){
        this.latitude = latitude
    }
    fun getLongitude():String? {
        return longitude
    }
    fun setLongitude(longitude:String){
        this.longitude = longitude
    }
    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status= status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(status: String) {
        this.message= status
    }
}

public class partiesClass {

    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("content")
    @Expose
    private var content: List<partyClass>? = null
    fun getStatus(): String? {
        return status
    }
    fun setStatus(status: String) {
        this.status = status
    }
    fun getMessage(): String? {
        return message
    }
    fun setMessage(message: String) {
        this.message = message
    }
    fun getContent(): List<partyClass>? {
        return content
    }

    fun setContent(content: List<partyClass>) {
        this.content = content
    }

}

class partyStatus {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: String? = null
    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

}

class poolSong {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("party_id")
    @Expose
    var partyId: Int? = null
    @SerializedName("frequency")
    @Expose
    var frequency: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("song_id")
    @Expose
    var songId: Int? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("artist")
    @Expose
    var artist: String? = null
    @SerializedName("duration")
    @Expose
    var duration: String? = null
    @SerializedName("album")
    @Expose
    var album: String? = null

}

class Pool {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("content")
    @Expose
    var content: List<poolSong>? = null

    fun getStatus1(): String? {
        return status
    }
    fun getMessage1(): String? {
        return message
    }
}