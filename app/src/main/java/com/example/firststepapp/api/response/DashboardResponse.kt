package com.example.firststepapp.api.response

import com.google.gson.annotations.SerializedName

data class DashboardResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("dashboardResult")
	val dashboardResult: DashboardResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class DashboardResult(

	@field:SerializedName("profilePicture")
	val profilePicture: Any? = null,

	@field:SerializedName("name")
	val name: String? = null
)
