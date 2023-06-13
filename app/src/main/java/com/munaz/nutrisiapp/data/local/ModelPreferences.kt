package com.munaz.nutrisiapp.data.local

import com.bumptech.glide.load.model.FileLoader

data class ModelPreferences(
    val name: String="",
    val email: String="",

    val usiaUser: Int? = null,
    val beratBadan: Int? = null,
    val tinggiBadan: Int? = null,
    val jenisKelamin: String? = null,
    val aktivitas: String?= null,
    val stress: String?= null
)
data class Tokenmodel(
    val token: String?=null
)