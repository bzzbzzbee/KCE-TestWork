package com.bzzbzzbee.kcetestwork.presentation.cars.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bzzbzzbee.kcetestwork.presentation.models.Option

class OptionDiffCallback : DiffUtil.ItemCallback<Option>() {
    override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean {
        return oldItem == newItem
    }
}