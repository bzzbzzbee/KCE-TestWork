package com.bzzbzzbee.kcetestwork.presentation.cars

import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.AddCarUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.DeleteCarUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.EditCarUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.GetCarsUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.GetFilteredRightHandedCarsUseCase
import com.bzzbzzbee.kcetestwork.presentation.base.BaseViewModel
import com.bzzbzzbee.kcetestwork.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    private val getFilteredRightHandedCarsUseCase: GetFilteredRightHandedCarsUseCase,
    private val addCarUseCase: AddCarUseCase,
    private val editCarUseCase: EditCarUseCase,
    private val deleteCarUseCase: DeleteCarUseCase
) : BaseViewModel() {
    private val _carsSF: MutableStateFlow<UiState<List<Car>>> = MutableStateFlow(UiState.Idle())
    val carsSF
        get() = _carsSF.asStateFlow()
}

