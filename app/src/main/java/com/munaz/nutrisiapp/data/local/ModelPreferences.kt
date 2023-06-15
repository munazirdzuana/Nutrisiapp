package com.munaz.nutrisiapp.data.local

import com.bumptech.glide.load.model.FileLoader

data class ModelPreferences(
    val name: String="",
    val email: String="",
    val usia_user: Int = 0,
    val beratBadan: Int =0,
    val tinggiBadan: Int =0 ,
    val jenisKelamin: String,
    val alergi: String,
    val penyakit: String,
    val aktivitas: String,
    val stress: String
)