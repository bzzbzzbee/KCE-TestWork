package com.bzzbzzbee.kcetestwork.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bzzbzzbee.kcetestwork.data.room.CarDB.Companion.FILES_TABLE_NAME

@Entity(tableName = FILES_TABLE_NAME)
data class CarFileDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val carId: Int,
    val isLocal: Boolean,
    val mimeType: CarFileMimeType,
    val resource: String
)