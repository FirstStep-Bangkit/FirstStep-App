package com.example.firststepapp.viewmodel

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firststepapp.api.ApiConfig
import com.example.firststepapp.api.response.AnswerResponse
import com.example.firststepapp.api.response.ChangePasswordResponse
import com.example.firststepapp.api.response.DashboardResponse
import com.example.firststepapp.api.response.DeleteProfileResponse
import com.example.firststepapp.api.response.DeleteUserResponse
import com.example.firststepapp.api.response.PersonalityResponse
import com.example.firststepapp.api.response.ProfileResponse
import com.example.firststepapp.api.response.QuizResponse
import com.example.firststepapp.api.response.UploadPhotoProfileResponse
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.ui.data.PredictRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MainViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    val dashboardResponse = MutableLiveData<DashboardResponse>()
    val _dashboardResponse: LiveData<DashboardResponse> = dashboardResponse

    val profileResponse = MutableLiveData<ProfileResponse>()
    val _profileResponse: LiveData<ProfileResponse> = profileResponse

    val changePasswordResponse = MutableLiveData<ChangePasswordResponse>()
    val _changePasswordResponse: LiveData<ChangePasswordResponse> = changePasswordResponse

    val deleteUserResponse = MutableLiveData<DeleteUserResponse>()
    val _deleteUserResponse: LiveData<DeleteUserResponse> = deleteUserResponse

    val quizResponse = MutableLiveData<QuizResponse>()
    val _quizResponse: LiveData<QuizResponse> = quizResponse

    val personalityResponse = MutableLiveData<PersonalityResponse>()
    val _personalityResponse: LiveData<PersonalityResponse> = personalityResponse

    private val answerResponse = MutableLiveData<AnswerResponse>()
    val _answerResponse: LiveData<AnswerResponse> = answerResponse

    private val deleteProfileResponse = MutableLiveData<DeleteProfileResponse>()
    val _deleteProfileResponse: LiveData<DeleteProfileResponse> = deleteProfileResponse

    private val uploadPhotoProfileResponse = MutableLiveData<UploadPhotoProfileResponse>()
    val _uploadPhotoProfileResponse: LiveData<UploadPhotoProfileResponse> = uploadPhotoProfileResponse


    companion object {
        private const val TAG = "MainViewModel"
    }

    fun dashboard(context: Context,token: String) {
        val client = ApiConfig.getApiService(context).dashboard(token)
        client.enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    dashboardResponse.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun profile(context: Context,token: String){
        val client = ApiConfig.getApiService(context).profile(token)
        client.enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    profileResponse.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun changePassword(context: Context,oldPassword: String, newPassword: String, token: String, callback: (Boolean) -> Unit){
        val client = ApiConfig.getApiService(context).changePassword(token,oldPassword,newPassword)
        client.enqueue(object : Callback<ChangePasswordResponse>{
            override fun onResponse(
                call: Call<ChangePasswordResponse>,
                response: Response<ChangePasswordResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    changePasswordResponse.value = response.body()
                    callback(true)
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                callback(false)
            }

        })
    }

    fun deleteUser(context: Context,token: String,username: String, callback: (Boolean) -> Unit){
        val client = ApiConfig.getApiService(context).deleteUser(token,username)
        client.enqueue(object : Callback<DeleteUserResponse>{
            override fun onResponse(
                call: Call<DeleteUserResponse>,
                response: Response<DeleteUserResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    deleteUserResponse.value = response.body()
                    callback(true)
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<DeleteUserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                callback(false)
            }

        })
    }

    fun getQuiz(context: Context,token: String){
        val client = ApiConfig.getApiService(context).quiz(token)
        client.enqueue(object : Callback<QuizResponse>{
            override fun onResponse(
                call: Call<QuizResponse>,
                response: Response<QuizResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    quizResponse.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<QuizResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun personality(context: Context,token: String) {
        val client = ApiConfig.getApiService(context).personality(token)
        client.enqueue(object : Callback<PersonalityResponse> {
            override fun onResponse(
                call: Call<PersonalityResponse>,
                response: Response<PersonalityResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    personalityResponse.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PersonalityResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun predict(context: Context, authorization: String, input: List<Int>) {
        val predictRequest = PredictRequest(input)
        val client = ApiConfig.getApiService(context).predict(authorization, predictRequest)
        client.enqueue(object : Callback<AnswerResponse> {
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    answerResponse.value = response.body()

                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {

                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun deleteProfile(context: Context, token: String, callback: (Boolean) -> Unit){
        val client = ApiConfig.getApiService(context).deleteProfile(token)
        client.enqueue(object : Callback<DeleteProfileResponse> {
            override fun onResponse(
                call: Call<DeleteProfileResponse>,
                response: Response<DeleteProfileResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    deleteProfileResponse.value = response.body()
                    callback(true)
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<DeleteProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                callback(false)
            }
        })
    }

    fun uploadPhotoProfile(context: Context, token: String, photo: File, callback: (Boolean) -> Unit){

        val requestImageFile = photo.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo_profile", photo.name, requestImageFile
        )

        val client = ApiConfig.getApiService(context).uploadPhoto(imageMultipart, token)
        client.enqueue(object : Callback<UploadPhotoProfileResponse>{
            override fun onResponse(
                call: Call<UploadPhotoProfileResponse>,
                response: Response<UploadPhotoProfileResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    uploadPhotoProfileResponse.value = response.body()
                    callback(true)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<UploadPhotoProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                callback(false)
            }

        })
    }
}
