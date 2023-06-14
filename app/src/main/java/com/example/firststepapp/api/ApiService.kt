package com.example.firststepapp.api

import com.example.firststepapp.api.response.AnswerResponse
import com.example.firststepapp.api.response.ChangePasswordResponse
import com.example.firststepapp.api.response.DashboardResponse
import com.example.firststepapp.api.response.DeleteUserResponse
import com.example.firststepapp.api.response.LoginResponse
import com.example.firststepapp.api.response.PersonalityResponse
import com.example.firststepapp.api.response.ProfileResponse
import com.example.firststepapp.api.response.QuizResponse
import com.example.firststepapp.api.response.RegisterResponse
import com.example.firststepapp.ui.data.PredictRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("dashboard")
    fun dashboard(
        @Header("Authorization9") authorization: String
    ): Call<DashboardResponse>

    @GET("profile")
    fun profile(
        @Header("Authorization") authorization: String
    ): Call<ProfileResponse>

    @POST("changepassword")
    @FormUrlEncoded
    fun changePassword(
        @Header("Authorization") authorization: String,
        @Field("currentPassword") currentPassword: String,
        @Field("newPassword") newPassword: String
    ): Call<ChangePasswordResponse>

    @DELETE("deleteuser/{username}")
    fun deleteUser(
        @Header("Authorization") authorization: String,
        @Path("username") username: String
    ): Call<DeleteUserResponse>

    @GET("questions")
    fun quiz(
        @Header("Authorization") authorization: String
    ): Call<QuizResponse>


    @GET("personality")
    fun personality(
        @Header("Authorization9") authorization: String
    ): Call<PersonalityResponse>

    @POST("predict")
    fun predict(
        @Header("Authorization") authorization: String,
        @Body request: PredictRequest
    ): Call<AnswerResponse>

}