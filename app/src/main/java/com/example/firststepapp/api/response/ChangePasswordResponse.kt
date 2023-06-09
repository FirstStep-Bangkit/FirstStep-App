package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)
