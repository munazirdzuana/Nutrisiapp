package com.munaz.nutrisiapp.error.mapper

interface Errorsrc {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}