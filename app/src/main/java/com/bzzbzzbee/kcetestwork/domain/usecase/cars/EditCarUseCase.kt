package com.bzzbzzbee.kcetestwork.domain.usecase.cars

import com.bzzbzzbee.kcetestwork.domain.entities.Car
import javax.inject.Inject

class EditCarUseCase @Inject constructor(private val repository: CarsRepository) {
    suspend operator fun invoke(car: Car) {
        repository.editCar(car)
    }
}