package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class AnswerResponse(

	@field:SerializedName("predicted_class")
	val predictedClass: Int? = null,

	@field:SerializedName("predicted_label")
	val predictedLabel: String? = null
)
