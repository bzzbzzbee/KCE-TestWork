package com.bzzbzzbee.kcetestwork.presentation.base

sealed class UiState<T> {
    class Idle<T>: UiState<T>()

    class Loading<T> : UiState<T>()

    data class Success<T>(val data: T) : UiState<T>()

    data class Error(val error: Throwable) : UiState<Nothing>()
}