package com.bzzbzzbee.kcetestwork.domain.usecase.cars

import com.bzzbzzbee.kcetestwork.domain.base.Resource
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCarsUseCase @Inject constructor(private val repository: CarsRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Car>>> =
        repository.getCars().flowOn(Dispatchers.IO)
}