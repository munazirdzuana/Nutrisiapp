package com.munaz.nutrisiapp.usecase.error

import com.munaz.nutrisiapp.error.Errorr

interface ErrorCase {
    fun getError(errorCode: Int): Errorr
}