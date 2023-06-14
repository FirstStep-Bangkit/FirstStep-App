package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("profileResult")
	val profileResult: ProfileResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class ProfileResult(

	@field:SerializedName("profilePicture")
	val profilePicture: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mbti")
	val mbti: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
