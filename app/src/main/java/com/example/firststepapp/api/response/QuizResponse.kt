package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class QuizResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("questions")
	val questions: List<String?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)
