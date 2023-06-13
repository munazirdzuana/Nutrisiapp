package com.munaz.nutrisiapp.data.request

data class RekomendasiReq(
    val usia_user: Float,
    val berat_badan: Float,
    val tinggi_badan: Float,
    val jenis_kelamin: String,
    val riwayat_penyakit: String,
    val riwayat_alergi: String,
    val faktor_aktivitas: String,
    val faktor_stress: String,
    val dislike_food: String?
)