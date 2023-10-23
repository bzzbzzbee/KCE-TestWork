package com.bzzbzzbee.kcetestwork.presentation.cars

import androidx.lifecycle.viewModelScope
import com.bzzbzzbee.kcetestwork.domain.base.Resource
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.AddCarUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.DeleteCarUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.EditCarUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.GetCarsUseCase
import com.bzzbzzbee.kcetestwork.domain.usecase.cars.GetFilteredRightHandedCarsUseCase
import com.bzzbzzbee.kcetestwork.presentation.base.BaseViewModel
import com.bzzbzzbee.kcetestwork.presentation.base.UiState
import com.bzzbzzbee.kcetestwork.presentation.models.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

    private val filterOption: MutableStateFlow<Option> = MutableStateFlow(Option.All)

    init {
        viewModelScope.launch {
            filterOption.collectLatest {
                when (it) {
                    Option.All -> getCars()
                    Option.Left -> getCars(false)
                    Option.Right -> getCars(true)
                }
            }
        }
    }

    private fun getCars() {
        viewModelScope.launch {
            getCarsUseCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        _carsSF.emit(UiState.Success(resource.data))
                    }
                }
            }
        }
    }

    private fun getCars(isRightHanded: Boolean) {
        viewModelScope.launch {
            getFilteredRightHandedCarsUseCase(isRightHanded).collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        _carsSF.emit(UiState.Success(resource.data))
                    }
                }
            }
        }
    }

    fun filterCars(option: Option) {
        viewModelScope.launch {
            filterOption.emit(option)
        }
    }

    fun addCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {//Не успевал разобраться с проблемами у Room с suspend модификатором. Все импорты в порядке, однако есть какие-то проблемы с версионками
            addCarUseCase(car)
        }
    }

    fun editCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            editCarUseCase(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCarUseCase(car)
        }
    }
}
