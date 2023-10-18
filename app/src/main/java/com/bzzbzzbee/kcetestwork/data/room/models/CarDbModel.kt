package com.bzzbzzbee.kcetestwork.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bzzbzzbee.kcetestwork.data.room.CarDB.Companion.CARS_TABLE_NAME

@Entity(tableName = CARS_TABLE_NAME)
data class CarDbModel(
    @PrimaryKey(autoGenerate = true)
    val carId: Int = 0,
    val name: String,
    val isRightHanded: Boolean,
    val color: String
)