package com.bzzbzzbee.kcetestwork.domain.usecase.cars

import com.bzzbzzbee.kcetestwork.domain.base.Resource
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredRightHandedCarsUseCase @Inject constructor(private val repository: CarsRepository) {
    suspend operator fun invoke(isRightHanded: Boolean): Flow<Resource<List<Car>>> = repository.getCarFilteredRightHanded(isRightHanded)
}