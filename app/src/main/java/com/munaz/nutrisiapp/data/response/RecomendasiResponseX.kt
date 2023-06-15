package com.munaz.nutrisiapp.data.response

import com.google.gson.annotations.SerializedName

data class RecomendasiResponseX(
    @field:SerializedName("data")
    val datas: List<Datas>
)