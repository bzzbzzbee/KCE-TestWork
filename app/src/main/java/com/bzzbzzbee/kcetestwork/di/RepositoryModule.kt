package com.bzzbzzbee.kcetestwork.di

import com.bzzbzzbee.kcetestwork.data.repository.CarsRepositoryImpl
import com.bzzbzzbee.kcetestwork.data.room.dao.CarsDao
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.CarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideCarsRepository(carsDao: CarsDao): CarsRepository =
        CarsRepositoryImpl(carsDao)
}