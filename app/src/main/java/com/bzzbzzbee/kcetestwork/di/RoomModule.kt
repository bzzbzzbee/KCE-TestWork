package com.bzzbzzbee.kcetestwork.di

import android.content.Context
import androidx.room.Room
import com.bzzbzzbee.kcetestwork.data.room.db.CarDB
import com.bzzbzzbee.kcetestwork.data.room.db.CarDB.Companion.CAR_DB_NAME
import com.bzzbzzbee.kcetestwork.data.room.dao.CarsDao
import com.bzzbzzbee.kcetestwork.data.room.callback.PrepopulateCarDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        callBack: PrepopulateCarDataBase
    ): CarDB {
        val dBbuilder = Room.databaseBuilder(context, CarDB::class.java, CAR_DB_NAME)
            .addCallback(callBack)

        return dBbuilder.build()
    }

    @Provides
    fun carsDao(db: CarDB): CarsDao = db.carsDao()
}