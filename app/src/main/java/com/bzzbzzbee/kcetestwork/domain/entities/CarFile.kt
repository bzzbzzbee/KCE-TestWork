package com.bzzbzzbee.kcetestwork.domain.entities

import android.os.Parcelable
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileMimeType
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarFile(
    val id: Int,
    val isLocal: Boolean,
    val mimeType: CarFileMimeType,
    val resource: String
): Parcelable