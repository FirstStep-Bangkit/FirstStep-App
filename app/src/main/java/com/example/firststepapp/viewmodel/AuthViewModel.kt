package com.example.firststepapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.firststepapp.api.ApiConfig
import com.example.firststepapp.api.response.LoginResponse
import com.example.firststepapp.api.response.LoginResult
import com.example.firststepapp.api.response.RegisterResponse
import com.example.firststepapp.preferences.UserPreferences
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel (private val preference : UserPreferences) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val registerResponse = MutableLiveData<RegisterResponse>()
    val _registerResponse : LiveData<RegisterResponse> = registerResponse

    val loginResponse =  MutableLiveData<LoginResponse>()
    val _loginResponse : LiveData<LoginResponse> = loginResponse

    companion object{
        private const val TAG = "AutentikasiViewModel"
    }

    fun register (token: Context, frontName: String, lastName: String, email: String, password: String, callback: (Boolean) -> Unit){
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).register(frontName, lastName, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    registerResponse.value = response.body()
                    callback(true)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                callback(false)
            }
        })
    }

    fun login (token: Context, email: String, password: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    loginResponse.value = response.body()

                    val token = response.body()!!.loginResult?.token
                    val name = response.body()!!.loginResult?.name
                    val email = response.body()!!.loginResult?.email
                    val username = response.body()!!.loginResult?.username
                    viewModelScope.launch {
                        if (token != null && name != null && email != null && username != null) {
                            preference.saveLoginSession(token, name, email, username)
                            Log.d("AutentikasiViewModel", "Saving token=$token, email=$email, name=$name, username=$username")
                        } else{
                            Log.d("AutentikasiViewModel", "ada yang kosong")
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getUserPreferences(property:String): LiveData<String> {
        return when(property){
            "email" -> preference.getEmail().asLiveData()
            "token" -> preference.getToken().asLiveData()
            "name" -> preference.getName().asLiveData()
            "username" -> preference.getUsername().asLiveData()
            else -> preference.getToken().asLiveData()
        }.also { liveData ->
            liveData.observeForever {
                Log.e("UserPreferences", "Retrieved user preference: $property =$it")
            }
        }
    }

    fun clearUserPreferences() {
        viewModelScope.launch {
            preference.clearLoginSession()
        }
    }
}