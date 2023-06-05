package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	var loginResult: LoginResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
    var message: String? = null
)

data class LoginResult(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
