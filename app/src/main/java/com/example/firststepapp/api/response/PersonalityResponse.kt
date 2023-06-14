package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class PersonalityResponse(

	@field:SerializedName("acronym")
	val acronym: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("mbti")
	val mbti: String? = null,

	@field:SerializedName("job")
	val job: String? = null
)
