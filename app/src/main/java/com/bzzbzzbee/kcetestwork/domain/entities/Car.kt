package com.bzzbzzbee.kcetestwork.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val carId: Int = 0,
    val name: String,
    val isRightHanded: Boolean,
    val color: String,
    val files: List<CarFile>
) : Parcelable
