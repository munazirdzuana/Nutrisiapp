package com.munaz.nutrisiapp.data.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(

	@field:SerializedName("data")
	val data: Data
)

data class Data(

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("predict")
	val predict: String
)
