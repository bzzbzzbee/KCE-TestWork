package com.bzzbzzbee.kcetestwork.presentation.cars

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarDetailViewModel @Inject constructor() : ViewModel() {
    private val emptyCar: Car = Car(
        0,
        "",
        true,
        "",
        emptyList()
    )

    private val _carSF: MutableStateFlow<Car> = MutableStateFlow(emptyCar)
    val carSF
        get() = _carSF.asStateFlow()

    private val _isValidSF: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isValidSF
        get() = _isValidSF.asStateFlow()

    private val actionSF: MutableStateFlow<UiAction> = MutableStateFlow(UiAction.Idle)

    init {
        viewModelScope.launch {
            actionSF.collectLatest(::handleUiAction)
        }

        viewModelScope.launch {
            _carSF.collectLatest { car ->
                _isValidSF.emit(isCarValid(car.name, car.color))
            }
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch {
            _carSF.emit(car)
        }
    }

    fun postUiAction(action: UiAction) {
        viewModelScope.launch {
            actionSF.emit(action)
        }
    }

    fun checkHexColor(hex: String): Int? {
        return try {
            Color.parseColor(hex)
        } catch (e: Exception) {
            null
        }
    }

    private fun handleUiAction(action: UiAction) {
        when (action) {
            UiAction.Idle -> {}
            is UiAction.ChangeColor -> updateCar(_carSF.value.copy(color = action.hexColor))
            is UiAction.ChangeFile -> updateCar(_carSF.value.copy(files = changeCarFile(action.carFile)))
            is UiAction.ChangeName -> updateCar(_carSF.value.copy(name = action.name))
            is UiAction.ChangeRightHanded -> updateCar(_carSF.value.copy(isRightHanded = action.isRightHanded))
            is UiAction.NewCar -> updateCar(action.car)
        }
    }

    private fun changeCarFile(carFile: CarFile): List<CarFile> {
        val oldFiles = _carSF.value.files.toMutableList()
        val isNewFile = oldFiles.find { it.id == carFile.id } == null

        if (isNewFile) {
            oldFiles.add(0, carFile)
        } else {
            oldFiles.replaceAll {
                if (it.id == carFile.id) carFile else it
            }
        }

        return oldFiles
    }

    private fun isCarValid(name: String, hex: String): Boolean =
        name.isNotEmpty() && checkHexColor(hex) != null
}

sealed class UiAction {
    data object Idle : UiAction()
    data class NewCar(val car: Car) : UiAction()
    data class ChangeName(val name: String) : UiAction()
    data class ChangeColor(val hexColor: String) : UiAction()
    data class ChangeRightHanded(val isRightHanded: Boolean) : UiAction()
    data class ChangeFile(val carFile: CarFile) : UiAction()
}