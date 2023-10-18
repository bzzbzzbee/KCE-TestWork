package com.bzzbzzbee.kcetestwork.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bzzbzzbee.kcetestwork.data.room.dao.CarsDao
import com.bzzbzzbee.kcetestwork.data.room.models.CarDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileDbModel

@Database(
    entities = [
        CarDbModel::class,
        CarFileDbModel::class
    ],
    version = 1
)
abstract class CarDB : RoomDatabase() {
    abstract fun carsDao(): CarsDao

    companion object {
        const val CAR_DB_NAME = "car_db_name"
        const val CARS_TABLE_NAME = "Cars"
        const val FILES_TABLE_NAME = "Files"
    }
}
