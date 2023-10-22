package com.bzzbzzbee.kcetestwork.presentation.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.bzzbzzbee.kcetestwork.databinding.ItemCarBinding
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile

class CarAdapter(private val onCarIconClick: (ImageView, CarFile) -> Unit) :
    ListAdapter<Car, CarViewHolder>(
        CarDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding, onCarIconClick)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
