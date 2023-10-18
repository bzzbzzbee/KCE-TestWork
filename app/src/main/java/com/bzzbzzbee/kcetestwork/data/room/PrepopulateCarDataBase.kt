package com.bzzbzzbee.kcetestwork.data.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bzzbzzbee.kcetestwork.data.room.CarsMockData.audiPics
import com.bzzbzzbee.kcetestwork.data.room.CarsMockData.bmwPics
import com.bzzbzzbee.kcetestwork.data.room.CarsMockData.cars
import com.bzzbzzbee.kcetestwork.data.room.CarsMockData.getCarPics
import com.bzzbzzbee.kcetestwork.data.room.CarsMockData.mercedesPics
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class PrepopulateCarDataBase @Inject constructor(private val database: Provider<CarDB>) :
    RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        prepopulate()
    }

    private fun prepopulate() {
        val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val carsDao = database.get().carsDao()

            cars.forEachIndexed { index, car ->
               val carId = carsDao.insert(car).toInt()

                when (index) {
                    0 -> {
                        val pics = getCarPics(carId, bmwPics)
                        carsDao.insert(pics)
                    }

                    1 -> {
                        val pics = getCarPics(carId, mercedesPics)
                        carsDao.insert(pics)
                    }

                    2 -> {
                        val pics = getCarPics(carId, audiPics)
                        carsDao.insert(pics)
                    }
                }
            }
        }
    }
}
