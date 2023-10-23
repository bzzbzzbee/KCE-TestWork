package com.bzzbzzbee.kcetestwork.data.room.callback

import com.bzzbzzbee.kcetestwork.data.room.models.CarDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileDbModel
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileMimeType.PHOTO

object CarsMockData {
    val cars = listOf(
        CarDbModel(
            name = "BMW",
            isRightHanded = false,
            color = "#696969"
        ),
        CarDbModel(
            name = "Mercedes",
            isRightHanded = true,
            color = "#e6e6fa"
        ),
        CarDbModel(
            name = "Audi",
            isRightHanded = false,
            color = "#000000"
        )
    )

    fun getCarPics(carId: Int, pics: List<String>): List<CarFileDbModel> = pics.map { url ->
        CarFileDbModel(
            carId = carId,
            isLocal = false,
            mimeType = PHOTO,
            resource = url
        )
    }

    val audiPics = listOf(
        "https://avatars.mds.yandex.net/get-verba/216201/2a00000161ddefa69716b0069a99a103fda8/cattouchret",
        "https://autopragmat.ru/upload/delight.webpconverter/upload/iblock/227/mglmjlqac1b2sy551pn8zaf2akqpz4fa.jpg.webp?169304200487486",
        "https://autopragmat.ru/upload/delight.webpconverter/upload/iblock/8c8/0r3ux4fbz7b3cb323yrrgpxfc99bz2hx.jpg.webp?169304200659492"
    )

    val bmwPics = listOf(
        "https://s.auto.drom.ru/i24235/c/photos/fullsize/bmw/x6/bmw_x6_902716.jpg",
        "https://s.auto.drom.ru/i24235/c/photos/fullsize/bmw/x6/bmw_x6_902686.jpg",
        "https://s.auto.drom.ru/i24235/c/photos/fullsize/bmw/x6/bmw_x6_902687.jpg"
    )

    val mercedesPics = listOf(
        "https://s.auto.drom.ru/i24279/c/photos/fullsize/mercedes-benz/eqe/mercedes-benz_eqe_1122978.jpg",
        "https://s.auto.drom.ru/i24279/c/photos/fullsize/mercedes-benz/eqe/mercedes-benz_eqe_1122979.jpg",
        "https://s.auto.drom.ru/i24279/c/photos/fullsize/mercedes-benz/eqe/mercedes-benz_eqe_1122980.jpg"
    )
}