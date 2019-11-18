package com.example.partyon
import retrofit2.Call
import retrofit2.http.*


interface APIService {
    @POST("/api/v1/user_login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<statusClass>

    @POST("/api/v1/user_register")
    @FormUrlEncoded
    fun register(
        @Field("name") name: String,
        @Field("lastName") lastName: String,
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<registerClass>

    @POST("/api/v1/join_party")
    @FormUrlEncoded
    fun join(
        @Field("code") code:String
    ): Call<partyClass>

    @POST("/api/v1/create_party")
    @FormUrlEncoded
    fun create(
        @Field("name") name:String,
        @Field("host_user_id") host_user_id:String,
        @Field("latitude") latitude:String,
        @Field("longitude") longitude:String
    ): Call<partyClass>

    @POST("/api/v1/evaluate_near_parties")
    @FormUrlEncoded
    fun closeParties(
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String
    ): Call<partiesClass>


    @GET
    fun searchSongs(@Url url:String) : Call<Songs>
}