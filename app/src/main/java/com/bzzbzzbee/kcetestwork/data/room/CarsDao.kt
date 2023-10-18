package com.bzzbzzbee.kcetestwork.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bzzbzzbee.kcetestwork.data.room.CarDB.Companion.CARS_TABLE_NAME
import com.bzzbzzbee.kcetestwork.data.room.models.CarDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarWithFiles
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {
    @Transaction
    @Query("select * from $CARS_TABLE_NAME")
    fun getCars(): Flow<List<CarWithFiles>>

    @Transaction
    @Query("select * from $CARS_TABLE_NAME where isRightHanded=:isRightHanded")
    fun getCarsFilteredRightHanded(isRightHanded: Boolean): Flow<List<CarWithFiles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(carFiles: List<CarFileDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(car: CarDbModel): Long
}