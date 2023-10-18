package com.bzzbzzbee.kcetestwork.domain.usecase.cars

import com.bzzbzzbee.kcetestwork.domain.base.Resource
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import kotlinx.coroutines.flow.Flow

interface CarsRepository {
    suspend fun getCars(): Flow<Resource<List<Car>>>
    suspend fun getCarFilteredRightHanded(isRightHanded: Boolean): Flow<Resource<List<Car>>>
    suspend fun addCar(car: Car)
    suspend fun editCar(car: Car)
    suspend fun deleteCar(car: Car)
}
