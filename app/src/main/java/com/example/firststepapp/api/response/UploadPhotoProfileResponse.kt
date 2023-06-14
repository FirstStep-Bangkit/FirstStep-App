package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class UploadPhotoProfileResponse(

	@field:SerializedName("photo_url")
	val photoUrl: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
