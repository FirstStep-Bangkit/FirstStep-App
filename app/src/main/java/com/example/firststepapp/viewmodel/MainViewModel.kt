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
import com.example.firststepapp.api.response.ChangePasswordResponse
import com.example.firststepapp.api.response.DashboardResponse
import com.example.firststepapp.api.response.ProfileResponse
import com.example.firststepapp.preferences.UserPreferences

class MainViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    val dashboardResponse = MutableLiveData<DashboardResponse>()
    val _dashboardResponse: LiveData<DashboardResponse> = dashboardResponse

    val profileResponse = MutableLiveData<ProfileResponse>()
    val _profileResponse: LiveData<ProfileResponse> = profileResponse

    val changePasswordResponse = MutableLiveData<ChangePasswordResponse>()
    val _changePasswordResponse: LiveData<ChangePasswordResponse> = changePasswordResponse

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
}
