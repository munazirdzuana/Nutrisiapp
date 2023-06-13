package com.munaz.nutrisiapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class AllergyResponse(

	@field:SerializedName("allergy")
	val allergy: List<AllergyItem>
)

data class AllergyItem(

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("uuid")
	val uuid: String
)
