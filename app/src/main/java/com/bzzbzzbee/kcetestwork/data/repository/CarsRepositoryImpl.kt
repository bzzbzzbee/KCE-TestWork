package com.bzzbzzbee.kcetestwork.data.repository

import com.bzzbzzbee.kcetestwork.data.mapper.CarsMapper.toCarDbModel
import com.bzzbzzbee.kcetestwork.data.mapper.CarsMapper.toCarFileDbModel
import com.bzzbzzbee.kcetestwork.data.mapper.CarsMapper.toCarsList
import com.bzzbzzbee.kcetestwork.data.room.dao.CarsDao
import com.bzzbzzbee.kcetestwork.domain.base.Resource
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.CarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(private val carsDao: CarsDao) : CarsRepository {
    override suspend fun getCars(): Flow<Resource<List<Car>>> = carsDao.getCars().map {
        Resource.Success(it.toCarsList())
    }

    override suspend fun getCarFilteredRightHanded(isRightHanded: Boolean): Flow<Resource<List<Car>>> =
        carsDao.getCars().map {
            Resource.Success(it.toCarsList())
        }

    override suspend fun addCar(car: Car) {
        val carDbModel = car.toCarDbModel()
        val id = carsDao.insert(carDbModel).toInt()
        addCarsFiles(id, car.files)
    }

    override suspend fun editCar(car: Car) {
        val carDbModel = car.toCarDbModel()
        val id = carsDao.insert(carDbModel).toInt()
        carsDao.deleteCarsFilesById(id)
        addCarsFiles(id, car.files)
    }

    private fun addCarsFiles(carId: Int, files: List<CarFile>) {
        val newFiles = files.map { it.toCarFileDbModel(carId) }
        carsDao.insert(newFiles)
    }

    override suspend fun deleteCar(car: Car) {
        carsDao.deleteCarsFilesById(car.carId)
        carsDao.deleteCarById(car.carId)
    }

}