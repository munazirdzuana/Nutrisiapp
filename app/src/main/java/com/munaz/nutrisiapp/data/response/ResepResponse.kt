package com.munaz.nutrisiapp.data.response

import com.google.gson.annotations.SerializedName

data class ResepResponse(

	@field:SerializedName("foods")
	val foods: List<FoodsItem>
)

data class FoodsItem(

	@field:SerializedName("foodDetail")
	val foodDetail: List<FoodDetailItem>,

	@field:SerializedName("foodRecipe")
	val foodRecipe: List<FoodRecipeItem>,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("foodTagsOnFood")
	val foodTagsOnFood: List<FoodTagsOnFoodItem>,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("deletedAt")
	val deletedAt: Any,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class FoodTagsOnFoodItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("deletedAt")
	val deletedAt: Any,

	@field:SerializedName("foodId")
	val foodId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("foodTagsId")
	val foodTagsId: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class FoodRecipeItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("deletedAt")
	val deletedAt: Any,

	@field:SerializedName("foodId")
	val foodId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class FoodDetailItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("deletedAt")
	val deletedAt: Any,

	@field:SerializedName("protein")
	val protein: Int,

	@field:SerializedName("carbohidrat")
	val carbohidrat: Int,

	@field:SerializedName("foodId")
	val foodId: Int,

	@field:SerializedName("fat")
	val fat: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("calories")
	val calories: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
