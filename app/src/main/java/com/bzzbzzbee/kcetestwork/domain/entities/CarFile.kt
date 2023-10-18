package com.bzzbzzbee.kcetestwork.domain.entities

import com.bzzbzzbee.kcetestwork.data.room.models.CarFileMimeType

data class CarFile(
    val id: Int,
    val isLocal: Boolean,
    val mimeType: CarFileMimeType,
    val resource: String
)