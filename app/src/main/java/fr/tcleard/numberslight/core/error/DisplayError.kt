package fr.tcleard.numberslight.core.error

import android.support.annotation.StringRes
import fr.tcleard.numberslight.R

class DisplayError(
        private val domain: ErrorDomain,
        private val type: ErrorType
) {

    @StringRes
    fun getTitle(): Int =
            when (domain) {
                ErrorDomain.LIST -> R.string.errorTitleList
                ErrorDomain.DETAIL -> R.string.errorTitleDetail
            }

    @StringRes
    fun getMessage(): Int =
            when (type) {
                ErrorType.DEFAULT -> R.string.errorMessageDefault
                ErrorType.NO_CONNECTION -> R.string.errorMessageNoConnection
                ErrorType.TIME_OUT -> R.string.errorMessageTimeOut
            }

}