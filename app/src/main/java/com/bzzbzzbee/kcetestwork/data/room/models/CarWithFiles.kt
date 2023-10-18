package com.bzzbzzbee.kcetestwork.data.room.models

import androidx.room.Embedded
import androidx.room.Relation

data class CarWithFiles(
    @Embedded val car: CarDbModel,
    @Relation(
        parentColumn = "carId",
        entityColumn = "carId",
    )
    val files: List<CarFileDbModel>
)