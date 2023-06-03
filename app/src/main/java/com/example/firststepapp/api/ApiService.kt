package com.example.firststepapp.api

import com.example.firststepapp.api.response.LoginResponse
import com.example.firststepapp.api.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    @FormUrlEncoded
    fun register(
        @Field("frontName") frontName: String,
        @Field("lastName") lastName : String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}