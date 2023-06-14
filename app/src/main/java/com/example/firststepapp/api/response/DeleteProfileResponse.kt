package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class DeleteProfileResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
