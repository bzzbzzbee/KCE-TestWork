package com.bzzbzzbee.kcetestwork.data.mapper

import com.bzzbzzbee.kcetestwork.data.room.models.CarDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarWithFiles
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile

object CarsMapper {
    fun Car.toCarDbModel(): CarDbModel =
        CarDbModel(
            carId = carId,
            name = name,
            isRightHanded = isRightHanded,
            color = color
        )

    fun CarFile.toCarFileDbModel(carId: Int): CarFileDbModel =
        CarFileDbModel(
            id = id,
            carId = carId,
            isLocal = isLocal,
            mimeType = mimeType,
            resource = resource
        )

    fun CarWithFiles.toCar(): Car =
        Car(
            carId = car.carId,
            name = car.name,
            isRightHanded = car.isRightHanded,
            color = car.color,
            files = files.toCarFile()
        )

    fun List<CarWithFiles>.toCarsList(): List<Car> =
        map { it.toCar() }

    fun CarFileDbModel.toCarFile(): CarFile =
        CarFile(
            id = id,
            isLocal = isLocal,
            mimeType = mimeType,
            resource = resource
        )

    fun List<CarFileDbModel>.toCarFile(): List<CarFile> =
        map { it.toCarFile() }
}