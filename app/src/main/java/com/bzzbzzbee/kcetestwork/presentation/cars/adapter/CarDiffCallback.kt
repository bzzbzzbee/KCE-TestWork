package com.bzzbzzbee.kcetestwork.presentation.cars.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bzzbzzbee.kcetestwork.domain.entities.Car

class CarDiffCallback : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.carId == newItem.carId
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}
