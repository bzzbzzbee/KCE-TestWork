package com.bzzbzzbee.kcetestwork.presentation.models

import androidx.annotation.StringRes
import com.bzzbzzbee.kcetestwork.R

sealed class Option(@StringRes val textRes: Int) {
    data object All: Option(R.string.all_handed)
    data object Right: Option(R.string.right_handed)
    data object Left: Option(R.string.left_handed)
}