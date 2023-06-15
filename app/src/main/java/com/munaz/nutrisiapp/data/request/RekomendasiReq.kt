package com.munaz.nutrisiapp.data.request

import okhttp3.RequestBody
import retrofit2.http.Part

data class RekomendasiReq(
    val usia_user: Int,
    val berat_badan: Int,
    val tinggi_badan: Int,
    val jenis_kelamin: String,
    val riwayat_penyakit: String,
    val riwayat_alergi: String,
    val faktor_aktivitas: String,
    val faktor_stress: String,
    val dislike_food: String ?
)