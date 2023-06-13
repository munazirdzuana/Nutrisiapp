package com.munaz.nutrisiapp.error.mapper

import android.content.Context
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.error.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) : Errorsrc {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(PASS_WORD_ERROR, getErrorString(R.string.invalid_password)),
            Pair(USER_NAME_ERROR, getErrorString(R.string.invalid_email)),
            Pair(CHECK_YOUR_FIELDS, getErrorString(R.string.invalid_email_and_password))
        ).withDefault { getErrorString(R.string.network_error) }
}