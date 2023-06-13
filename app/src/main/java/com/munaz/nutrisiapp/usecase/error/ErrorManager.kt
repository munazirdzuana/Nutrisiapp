package com.munaz.nutrisiapp.usecase.error

import com.munaz.nutrisiapp.error.Errorr
import com.munaz.nutrisiapp.error.mapper.ErrorMapper
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorCase {
    override fun getError(errorCode: Int): Errorr {
        return Errorr(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}