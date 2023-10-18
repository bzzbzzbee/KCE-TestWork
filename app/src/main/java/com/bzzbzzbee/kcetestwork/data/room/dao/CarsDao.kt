package com.bzzbzzbee.kcetestwork.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bzzbzzbee.kcetestwork.data.room.db.CarDB.Companion.CARS_TABLE_NAME
import com.bzzbzzbee.kcetestwork.data.room.db.CarDB.Companion.FILES_TABLE_NAME
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

    @Query("delete from $FILES_TABLE_NAME where id in (:ids)")
    fun deleteFilesByIds(ids: List<Int>)

    @Query("delete from $CARS_TABLE_NAME where carId=:id")
    fun deleteCarById(id: Int)

    @Query("delete from $FILES_TABLE_NAME where id=:carId")
    fun deleteCarsFilesById(carId: Int)
}